import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Server {
    static String gameState;
    private static List<ClientHandler> connectedClients = new ArrayList<>();

    public static void main(String... args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(4444);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    broadCast();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 0, 5000);

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

    private static void broadCast() throws Exception {
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
            client.dataOutputStream.writeBytes(Server.gameState + "\n");
            client.dataOutputStream.flush();
        }
    }
}