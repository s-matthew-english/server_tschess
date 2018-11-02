import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.TimerTask;

public class BlockchainPoller extends TimerTask {

    @Override
    public void run() {
        MediaType json = MediaType.parse("application/json; charset=utf-8");
        String params = "[{ \"from\": \"0xb60e8dd61c5d32be8058bb8eb970870f07233155\", \"to\": \"0x739e977f0b1cf20268873d4c2d58e9c88adebea6\", \"gas\": \"0x76c0\", \"gasPrice\": \"0x9184e72a000\", \"data\": \"0xc2985578\" }]";
        String content = "{\"jsonrpc\":\"2.0\",\"id\":67,\"method\":\"eth_call\",\"params\":" + params + "}";

        RequestBody body = RequestBody.create(json, content);

        String url = "https://kovan.infura.io";
        Request request = new Request.Builder().post(body).url(url).build();
        OkHttpClient client = new OkHttpClient();
        try {
            Response resp = client.newCall(request).execute();
            System.out.println(resp.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
