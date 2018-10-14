import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientHandler extends Thread {
    private Socket socket;
    DataOutputStream dataOutputStream = null;

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

            while (true) {
                Server.gameState = bufferedReader.readLine();

                System.out.println(Server.gameState);

//                dataOutputStream.writeBytes(Server.gameState + "\n");
//                dataOutputStream.flush();
            }

        } catch (Exception e) {
            System.out.println("error");
        }
    }
}