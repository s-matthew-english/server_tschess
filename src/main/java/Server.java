import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

// Server class
public class Server {
    static String gameState;
    public static List<Socket> connectedClients = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(4444);

        while (true) {
            Socket socket;

            try {
                socket = serverSocket.accept();

                System.out.println("A new client is connected : " + socket);
                if(!connectedClients.contains(socket)){
                    System.out.println("Assigning new thread for this client");
                    connectedClients.add(socket);
                    System.out.println("size: " + connectedClients.size());
                }

                // create a new thread object
                Thread t = new ClientHandler(socket);
                // Invoking the start() method
                t.start();

            } catch (Exception e) {
                System.out.println("error!");
                e.printStackTrace();
            }
        }
    }
}