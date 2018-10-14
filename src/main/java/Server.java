import java.io.*;
import java.net.*;

// Server class 
public class Server {
    public static void main(String[] args) throws IOException {

        int port = 4444;
        // server is listening on port 4444
        ServerSocket ss = new ServerSocket(port);

        // running infinite loop for getting 
        // client request 
        while (true) {
            Socket s = null;

            try {
                // socket object to receive incoming client requests 
                s = ss.accept();

                System.out.println("A new client is connected : " + s);
                System.out.println("Assigning new thread for this client");

                // create a new thread object 
                Thread t = new ClientHandler(s);

                // Invoking the start() method 
                t.start();

            }
            catch (Exception e) {
                s.close();
                e.printStackTrace();
            }
        }
    }
}