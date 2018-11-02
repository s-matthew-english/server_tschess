import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

public class BlockchainPoller {

    public static void hitInfura() {

        Runnable myRunnable =
                () -> {
                    MediaType json = MediaType.parse("application/json; charset=utf-8");
                    String content = "{\"jsonrpc\":\"2.0\",\"id\":67,\"method\":\"web3_clientVersion\"}";
                    RequestBody body = RequestBody.create(json, content);

                    String url = "https://mainnet.infura.io";
                    Request request = new Request.Builder().post(body).url(url).build();

                    OkHttpClient client = new OkHttpClient();
                    try {
                        Response resp = client.newCall(request).execute();
                        System.out.println(resp.body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                };

        myRunnable.run();
        myRunnable.run();
    }
}
