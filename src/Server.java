import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    private static Server server;

    private ServerSocket serverSocket;

    private ArrayList<ClientHandler> clients = new ArrayList<>();

    private Server() {
        try {
            this.serverSocket = new ServerSocket(3040);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Server getInstance() {
        return (server == null) ? server = new Server() : server;
    }


    public void startServer() {

        try {
            while (!serverSocket.isClosed()) {
                Socket localSocket = serverSocket.accept();
                ClientHandler client = new ClientHandler(localSocket);
                Runnable read = () -> {
                    client.readMsg();
                };
                Thread thread = new Thread(read);
                thread.start();
                clients.add(client);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void broadcastMassage(String msg, int port) {
        for (ClientHandler clientHandler : clients) {
            if (port != clientHandler.getSocket().getPort()) {
                clientHandler.writeMsg(msg);
            }
        }
    }


}

