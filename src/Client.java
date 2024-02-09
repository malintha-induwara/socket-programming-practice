import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {

    private  static final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        try {

            Socket localSocket = new Socket("localhost",3040);

            DataInputStream inputStream = new DataInputStream(localSocket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(localSocket.getOutputStream());

            Runnable runnable = ()->{
                try {
                    while (!localSocket.isClosed()) {
                        String msg = inputStream.readUTF();
                        System.out.println("Server: " + msg);
                    }
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            };

            Thread thread = new Thread(runnable);
            thread.start();


            while (!localSocket.isClosed()){
                try{
                    String msg = bufferedReader.readLine();
                    outputStream.writeUTF(msg);
                    outputStream.flush();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

