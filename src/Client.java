import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 2000);
            System.out.println("Successfully connected to the Server!");

            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream  dataInputStream = new DataInputStream(socket.getInputStream());
            Scanner input = new Scanner(System.in);

            //Can use lambda expression because runnable is functional interface
            Runnable runnable = ()->{
                try {
                    String serverMassage;
                    do {
                        serverMassage = dataInputStream.readUTF();
                        System.out.println("Server: " + serverMassage);
                    } while (!serverMassage.equals("end"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            };

            Thread thread = new Thread(runnable);
            thread.start();


            String client;
            do {
                client=input.nextLine();
                dataOutputStream.writeUTF(client);
                dataOutputStream.flush();
            }while (!client.equals("end"));


            socket.close();
            dataOutputStream.close();
            dataInputStream.close();
            input.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
