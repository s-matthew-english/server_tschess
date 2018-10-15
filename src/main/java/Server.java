import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    static String gameState;
    private static List<ClientHandler> connectedClients = new ArrayList<>();

    public static void main(String... args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(4444);

        while (true) {
            Socket socket;
            try {
                socket = serverSocket.accept();
                System.out.println("A new client is connected: " + socket);

                ClientHandler client = new ClientHandler(socket);
                client.start();

                connectedClients.add(client);
            } catch (Exception e) {
                System.out.println("error!");
                e.printStackTrace();
            }
        }
    }

    public static void broadCast() {
        if (connectedClients == null) {
            return;
        }
        for (ClientHandler client : connectedClients) {
            if (client == null) {
                return;
            }
            if (client.dataOutputStream == null) {
                return;
            }
            System.out.println("broadCast");
            Thread t = new Thread(() -> {
                try {
                    client.dataOutputStream.writeBytes(Server.gameState + "\n");
                    client.dataOutputStream.flush();
                } catch (IOException e) {
                }
            });
            t.start();
        }
    }
}