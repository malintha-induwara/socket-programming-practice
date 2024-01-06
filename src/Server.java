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

            DataInputStream dataInputStream = new DataInputStream(localSocket.getInputStream());

            DataOutputStream dataOutputStream = new DataOutputStream(localSocket.getOutputStream());


            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));


            String reply;
            String message;

            do {
                reply=bufferedReader.readLine();
                dataOutputStream.writeUTF(reply);
                dataOutputStream.flush();

                message = dataInputStream.readUTF();
                System.out.println("Client: " + message);
            }while (!message.equals("end"));

            localSocket.close();
            dataOutputStream.close();
            dataInputStream.close();
            bufferedReader.close();

        }catch (IOException e){

        }
    }
}
