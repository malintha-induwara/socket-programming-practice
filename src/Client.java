import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {



        try {
            Socket socket = new Socket("localhost",2000);
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

            dataOutputStream.writeUTF("Hello Server");
            dataOutputStream.flush();



            dataOutputStream.close();
            socket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}

