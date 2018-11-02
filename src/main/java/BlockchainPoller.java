import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.net.URL;

public class BlockchainPoller {

    public static void hitInfura() throws Exception {

        OkHttpClient client = new OkHttpClient();
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");

        final RequestBody body =
                RequestBody.create(
                        JSON,
                        "{\"jsonrpc\":\"2.0\",\"id\":" + " 67 " + ",\"method\":\"web3_clientVersion\"}");
        final Request request = new Request.Builder().post(body).url(new URL("https://mainnet.infura.io")).build();

        Response resp = client.newCall(request).execute();

        System.out.println(resp.body().string());
    }
}
