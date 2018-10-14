import java.net.ServerSocket;
import java.net.Socket;

// Server class
public class Server {
    static String gameState;

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(4444);

        while (true) {
            Socket socket;

            try {
                socket = serverSocket.accept();
                System.out.println("A new client is connected : " + socket);

                ClientHandler client = new ClientHandler(socket);
                client.start();

            } catch (Exception e) {
                System.out.println("error!");
                e.printStackTrace();
            }
        }
    }
}