import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {

            //Creating Server socket
            ServerSocket serverSocket = new ServerSocket(2000);
            System.out.println("Server started");

            //Accept the connection and Creating Local Socket
            Socket localSocket = serverSocket.accept();

            System.out.println("Client Request Accepted!");



            DataInputStream dataInputStream = new DataInputStream(localSocket.getInputStream());

            //Reading the massage
            String massage = dataInputStream.readUTF();
            System.out.println("Client: "+massage);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

