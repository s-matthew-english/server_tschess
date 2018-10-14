import java.net.ServerSocket;
import java.net.Socket;

// Server class
public class Server {
    public static String gameState;

    public static void main(String[] args) throws Exception {

        // server is listening on port 4444
        ServerSocket serverSocket = new ServerSocket(4444);

        // running infinite loop for getting
        // client request
        while (true) {
            Socket socket;

            try {
                // socket object to receive incoming client requests
                socket = serverSocket.accept();

                System.out.println("A new client is connected : " + socket);
                System.out.println("Assigning new thread for this client");

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