import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {

        try {
            // Creating Server socket
            ServerSocket serverSocket = new ServerSocket(2000);
            System.out.println("Server started");

            // Accept the connection and Creating Local Socket
            Socket localSocket = serverSocket.accept();
            System.out.println("Client Request Accepted!");
            System.out.println();

            DataInputStream dataInputStream = new DataInputStream(localSocket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(localSocket.getOutputStream());
            Scanner input = new Scanner(System.in);



            Runnable runnable = ()->{
                try {
                    String clientMassage;
                    do {
                        clientMassage = dataInputStream.readUTF();
                        System.out.println("Client: " + clientMassage);

                    }while (!clientMassage.equals("end"));

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            };

            Thread thread = new Thread(runnable);
            thread.start();


            String serverMassage;
            do {
                serverMassage=input.nextLine();
                dataOutputStream.writeUTF(serverMassage);
                dataOutputStream.flush();
            }while (!serverMassage.equals("end"));



            localSocket.close();
            dataOutputStream.close();
            dataInputStream.close();
            input.close();

        }catch (IOException e){
           throw new RuntimeException();
        }
    }
}
