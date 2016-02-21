package me.uteacher.www.herocat.util.SNSUtil;

import android.content.Intent;
import android.os.Bundle;

import java.io.InputStream;

/**
 * Created by HL0521 on 2016/2/19.
 */
public interface ISNSUtil {

    public void onCreate(Bundle savedInstanceState, Intent intent);

    public void onDestroy();

    public void onActivityResult(int requestCode, int resultCode, Intent intent);

    public void onNewIntent(Intent intent);

    public void shareToWechat(String webUrl, String title, String description, InputStream imageInput, boolean timeline);

    public void shareToQQ(String webUrl, String title, String description, String imageUrl, String appName);

    public void shareToWeibo(String webUrl, String title, String description, InputStream imageInput);

    public void loginWeixin();

    public void loginQQ(SNSLoginCallback callback);

    public void loginWeibo(SNSLoginCallback callback);

}
