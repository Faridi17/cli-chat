import java.io.*;
import java.net.*;
import java.util.Scanner;

class TCPClient {
    public static void main(String[] args) throws Exception {
        String sectence = "";
        String modifiedSectence;

        Scanner sc = new Scanner (System.in);
        Socket clientSocket = new Socket("10.9.57.44", 50100); // what can inserted in this line?
        
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        while(true) {
            sectence = sc.nextLine();

            if (sectence.equalsIgnoreCase("bye")) {
                clientSocket.close();
                System.exit(0);
            }
            
            outToServer.writeBytes(sectence + '\n');
            modifiedSectence = inFromServer.readLine();
            System.out.println("FROM SERVER: " + modifiedSectence);

            if (modifiedSectence.equalsIgnoreCase("bye")) {
                System.out.println("Server menutup");
                clientSocket.close();
                System.exit(0);
            }
        }
    }
}