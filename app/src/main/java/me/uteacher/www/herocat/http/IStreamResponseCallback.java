package me.uteacher.www.herocat.http;

import java.io.InputStream;

/**
 * Created by HL0521 on 2016/1/25.
 */
public interface IStreamResponseCallback {

    public void onSuccess(InputStream inputStream);

    public void onFailure(String error);
    
}
