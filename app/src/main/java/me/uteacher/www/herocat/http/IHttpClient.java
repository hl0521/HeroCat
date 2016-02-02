package me.uteacher.www.herocat.http;

/**
 * Created by HL0521 on 2016/1/25.
 */
public interface IHttpClient {

    public void get(String url, IJsonResponseCallback callback);

    public void get(String url, IStreamResponseCallback callback);

}
