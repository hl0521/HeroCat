package me.uteacher.www.herocat.http;

/**
 * Created by HL0521 on 2016/1/25.
 */
public class HttpClientFactory {

    private static IHttpClient httpClient;

    public static IHttpClient getHttpClient() {
        if (httpClient == null) {
            httpClient = new OkHttpClientImpl();
        }

        return httpClient;
    }
}
