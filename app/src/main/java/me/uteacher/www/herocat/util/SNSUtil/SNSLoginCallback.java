package me.uteacher.www.herocat.util.SNSUtil;

/**
 * Created by HL0521 on 2016/2/19.
 */
public interface SNSLoginCallback {

    public void onSuccess(String username, String token, String id, String expires, String refresh);

    public void onFailure(String error);

    public void onCancel();

}
