import java.io.*;
import java.util.Scanner;

public class TCPServer {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int[] angka = new int[10];

        try {
            System.out.println("Masukkan 30 angka integer:");
            for (int i = 0; i < angka.length; i++) {
                System.out.print("Angka ke-" + (i + 1) + ": ");
                angka[i] = input.nextInt();
            }

            FileWriter fw = new FileWriter("Nilai.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (int n : angka) {
                bw.write(Integer.toString(n));
                bw.newLine();
            }
            bw.flush();
            bw.close();
            fw.close();

            System.out.println("File Nilai.txt sudah berhasil ditulis di: " + new File("Nilai.txt").getAbsolutePath());

            FileReader fr = new FileReader("Nilai.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;

            System.out.println("\nAngka yang bernilai >= 80:");
            while ((line = br.readLine()) != null) {
                int n = Integer.parseInt(line);
                if (n >= 80) {
                    System.out.println(n);
                }
            }
            br.close();
            fr.close();

        } catch (IOException e) {
            System.out.println("Terjadi error: " + e.getMessage());
        }
    }
}
