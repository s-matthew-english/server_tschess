import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientHandler extends Thread {
    private Socket socket;
    DataOutputStream dataOutputStream = null;
    boolean initialMessage = true;

    ClientHandler(Socket clientSocket) {
        this.socket = clientSocket;
    }

    @Override
    public void run() {
        InputStream inputStream;
        BufferedReader bufferedReader;

        try {
            inputStream = socket.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            String clientInputMessage;

            if (initialMessage && (Server.connectedClients.size() == 1)) {
                dataOutputStream.writeBytes("WHITE" + "\n");
                initialMessage = false;
            }
            if (initialMessage && (Server.connectedClients.size() == 2)) {
                dataOutputStream.writeBytes("BLACK" + "\n");
                initialMessage = false;
            }

            while ((clientInputMessage = bufferedReader.readLine()) != null) {
                Server.gameState = clientInputMessage;
                System.out.println(Server.gameState);
                Server.broadCast();
            }

        } catch (Exception e) {
            System.out.println("error");
        }
    }
}

