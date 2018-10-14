import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

// Server class 
public class Server {
    public static String gameState;

    public static void main(String[] args) throws IOException {
        int port = 4444;
        // server is listening on port 4444
        ServerSocket ss = new ServerSocket(port);

        // running infinite loop for getting
        // client request
        while (true) {
            Socket socket = null;

            try {
                // socket object to receive incoming client requests
                socket = ss.accept();

                System.out.println("A new client is connected : " + socket);
                System.out.println("Assigning new thread for this client");

                // create a new thread object
                Runnable t = new ClientHandler(socket);
                // Invoking the start() method
                t.run();

                List<Runnable> connectedClients = new ArrayList<>();
                connectedClients.add(t);


                for (Runnable client : connectedClients) {
                    ClientHandler clientHandler = (ClientHandler) client;

                    System.out.println("clientHandler: " + clientHandler.socket.toString());

                    clientHandler.out.writeBytes(Server.gameState + "\n");
                    clientHandler.out.flush();
                }


            } catch (Exception e) {
                socket.close();
                e.printStackTrace();
            }
        }
    }
}