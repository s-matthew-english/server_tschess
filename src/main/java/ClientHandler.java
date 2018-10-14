import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class ClientHandler extends Thread {
    private Socket socket;

    ClientHandler(Socket clientSocket) {
        this.socket = clientSocket;
    }

    @Override
    public void run() {
        InputStream inputStream;
        BufferedReader bufferedReader;
        DataOutputStream dataOutputStream;

        try {
            inputStream = socket.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            String clientInputMessage;
            while ((clientInputMessage = bufferedReader.readLine()) != null) {
                Server.gameState = clientInputMessage;
                System.out.println(Server.gameState);
            }

            // says "foo" every half second
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    try {
                        System.out.println("transmitting message...");
                        dataOutputStream.writeBytes(Server.gameState + "\n");
                        dataOutputStream.flush();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, 0, 500);

        } catch (Exception e) {
            System.out.println("error");
        }
    }
}