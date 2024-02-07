import java.io.*;
import java.net.Socket;

public class ClientHandler {

    private Socket socket;

    private DataInputStream dataInputStream;

    private DataOutputStream dataOutputStream;


    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            this.dataInputStream = new DataInputStream(socket.getInputStream());
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public void readMsg(){
        try {
            while (!socket.isClosed()) {
                String msg=dataInputStream.readUTF();
                Server.getInstance().broadcastMassage(msg, socket.getPort());
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }


    public void writeMsg(String msg){
        try {
            dataOutputStream.writeUTF(msg);
            dataOutputStream.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return socket;
    }
}

