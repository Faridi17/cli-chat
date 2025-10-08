import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TCPServer {
    public static void main(String argv[]) throws Exception {
        ServerSocket welcomeSocket = new ServerSocket(50100);
        Scanner sc = new Scanner(System.in);

        System.out.println("Server berjalan di port 50100...");

        while (true) {
            Socket connectionSocket = welcomeSocket.accept();
            System.out.println("Client terhubung: " + connectionSocket.getInetAddress());

            BufferedReader inFromClient =
                    new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
            DataOutputStream outToClient =
                    new DataOutputStream(connectionSocket.getOutputStream());

            while (true) {
                String clientSentence = inFromClient.readLine();

                // kalau client menutup koneksi
                if (clientSentence == null) {
                    System.out.println("Client memutuskan koneksi. Server ikut berhenti...");
                    connectionSocket.close();
                    welcomeSocket.close();
                    System.exit(0); // keluar dari program
                }

                System.out.println("Dari Client: " + clientSentence);

                System.out.print("Balasan ke Client: ");
                String reply = sc.nextLine();

                outToClient.writeBytes(reply + '\n');
                System.out.println("Pesan dikirim ke client: " + reply);

                // kalau server balas "bye", server juga berhenti
                if (reply.equalsIgnoreCase("bye")) {
                    System.out.println("Server menutup koneksi...");
                    connectionSocket.close();
                    welcomeSocket.close();
                    System.exit(0);
                }
            }
        }
    }
}
