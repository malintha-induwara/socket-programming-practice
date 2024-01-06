import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 2000);
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            String reply;
            String message;

            do {
                reply=bufferedReader.readLine();
                dataOutputStream.writeUTF(reply);
                dataOutputStream.flush();

                message = dataInputStream.readUTF();
                System.out.println("Server: " + message);
            }while (!message.equals("end"));

            socket.close();
            dataOutputStream.close();
            dataInputStream.close();
            bufferedReader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
