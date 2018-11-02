import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class BlockchainPoller {

    public static void hitInfura() throws Exception {
        MediaType json = MediaType.parse("application/json; charset=utf-8");
        String content = "{\"jsonrpc\":\"2.0\",\"id\":67,\"method\":\"web3_clientVersion\"}";
        RequestBody body = RequestBody.create(json, content);

        String url = "https://mainnet.infura.io";
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().post(body).url(url).build();
        Response resp = client.newCall(request).execute();

        System.out.println(resp.body().string());
    }
}
