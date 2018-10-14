import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

// Server class
public class Server {
    static String gameState;
    public static List<ClientHandler> connectedClients = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(4444);

        while (true) {
            Socket socket;

            try {
                socket = serverSocket.accept();

                Thread client = new ClientHandler(socket);

                System.out.println("A new client is connected : " + socket);
                if (!connectedClients.contains(client)) {
                    System.out.println("Assigning new thread for this client");
                    connectedClients.add((ClientHandler)client);
                }
                client.start();

                for (ClientHandler activeClient : connectedClients) {
                    activeClient.dataOutputStream.writeBytes(Server.gameState + "\n");
                    activeClient.dataOutputStream.flush();
                }

            } catch (Exception e) {
                System.out.println("error!");
                e.printStackTrace();
            }
        }
    }
}