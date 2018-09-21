package as.bwei.com.zhoukao3ayingyuan.model;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by HP on 2018/9/21.
 */

public class ShowModel {
    private OkHttpClient okHttpClient;

    public void show(final ShowCallBack showCallBack){
        okHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://172.17.8.100/movieApi/cinema/v1/findRecommendCinemas?longitude=116.30551391385724&latitude=40.04571807465411&page=1&count=10")
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                showCallBack.failure("失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                showCallBack.success(string);
            }
        });
    }

    public interface ShowCallBack {
        void failure(String msg);

        void success(String msg);
    }
}
