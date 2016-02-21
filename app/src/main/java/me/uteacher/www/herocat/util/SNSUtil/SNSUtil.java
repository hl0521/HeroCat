package me.uteacher.www.herocat.util.SNSUtil;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.avos.avoscloud.okhttp.internal.Util;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import java.io.InputStream;

import me.uteacher.www.herocat.R;
import me.uteacher.www.herocat.config.DefaultAppConfig;
import me.uteacher.www.herocat.config.WeChatConfig;

/**
 * Created by HL0521 on 2016/2/19.
 */
public class SNSUtil implements ISNSUtil {

    private static final String TAG = "SNSUtil";

    private Context context;

    private IWXAPI wxapi;

    public SNSUtil(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState, Intent intent) {
        regToWX();
    }

    @Override
    public void onDestroy() {
        if (wxapi != null) {
            wxapi.unregisterApp();
        }
    }

    private void regToWX() {
        if (context != null) {
            wxapi = WXAPIFactory.createWXAPI(context, WeChatConfig.APP_ID, true);
            wxapi.registerApp(WeChatConfig.APP_ID);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

    }

    @Override
    public void onNewIntent(Intent intent) {

    }

    @Override
    public void shareToWechat(String webUrl, String title, String description, InputStream imageInput, boolean timeline) {
        // 初始化一个 WXWebpageObject ，填写 url
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = webUrl;

        // 用 WXWebpageObject 对象初始化一个 WXMediaMessage 对象，填写标题、描述
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = title;
        msg.description = description;
        Bitmap thumb = me.uteacher.www.herocat.util.Util.resizeBitmap(BitmapFactory.decodeStream(imageInput), 100, 100);
        msg.thumbData = me.uteacher.www.herocat.util.Util.bmpToByteArray(thumb, true);

        // 构造一个 Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        // transaction 字段用于唯一标识一个请求
        req.transaction = buildTransaction("webPage");
        req.message = msg;
        req.scene = timeline ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;

        wxapi.sendReq(req);
    }

    @Override
    public void shareToQQ(String webUrl, String title, String description, String imageUrl, String appName) {

    }

    @Override
    public void shareToWeibo(String webUrl, String title, String description, InputStream imageInput) {

    }

    @Override
    public void loginWeixin() {

    }

    @Override
    public void loginQQ(SNSLoginCallback callback) {

    }

    @Override
    public void loginWeibo(SNSLoginCallback callback) {

    }

    private String buildTransaction(String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

}
