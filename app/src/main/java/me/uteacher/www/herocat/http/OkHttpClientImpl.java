package me.uteacher.www.herocat.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.avos.avoscloud.okhttp.Callback;
import com.avos.avoscloud.okhttp.OkHttpClient;
import com.avos.avoscloud.okhttp.Request;
import com.avos.avoscloud.okhttp.Response;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by HL0521 on 2016/1/25.
 */
public class OkHttpClientImpl extends BaseHttpClient implements IHttpClient {

    public OkHttpClientImpl() {

    }

    private OkHttpClient okHttpClient = new OkHttpClient();

    @Override
    public void get(String url, final IJsonResponseCallback callback) {
        Request request = new Request.Builder().url(url).build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                callback.onFailure(e.getMessage());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code" + response);
                }

                String string = response.body().string();
                JSONObject jsonObject = JSON.parseObject(string);
                callback.onSuccess(jsonObject);
            }
        });
    }

    @Override
    public void get(String url, final IStreamResponseCallback callback) {
        Request request = new Request.Builder().url(url).build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                callback.onFailure(e.getMessage());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code" + response);
                }

                InputStream inputStream = response.body().byteStream();
                callback.onSuccess(inputStream);
            }
        });
    }
}
