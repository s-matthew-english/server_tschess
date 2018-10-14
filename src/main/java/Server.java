import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Server {
    static String gameState;
    private static List<ClientHandler> connectedClients = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(4444);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    System.out.println("transmitting message...");
                    broadCast();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 0, 500);


        while (true) {
            Socket socket;

            try {
                socket = serverSocket.accept();
                System.out.println("A new client is connected : " + socket);

                ClientHandler client = new ClientHandler(socket);
                client.run();

                connectedClients.add(client);

            } catch (Exception e) {
                System.out.println("error!");
                e.printStackTrace();
            }
        }
    }

    private static void broadCast() throws Exception {
        for (ClientHandler activeClient : connectedClients) {
            if (activeClient.dataOutputStream == null) {
                return;
            }
            activeClient.dataOutputStream.writeBytes(Server.gameState + "\n");
            activeClient.dataOutputStream.flush();
        }
    }
}