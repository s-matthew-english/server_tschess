import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientHandler extends Thread {
    private Socket socket;
    DataOutputStream dataOutputStream;

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
            while ((clientInputMessage = bufferedReader.readLine()) != null) {
                Server.gameState = clientInputMessage;

                System.out.println(Server.gameState);
            }
        } catch (Exception e) {
            System.out.println("error");
        }
    }
}