package me.uteacher.www.herocat.wxapi;


import android.app.Activity;
import android.os.Bundle;

import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import me.uteacher.www.herocat.config.WeChatConfig;

/**
 * Created by HL0521 on 2016/2/17.
 */
public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "WXEntryActivity";

    private IWXAPI wxapi;
    private IWXPresenter wxPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        System.out.println(TAG + "onCreate");

        wxapi = WXAPIFactory.createWXAPI(this, WeChatConfig.APP_ID, true);
        // 注册到微信
        regToWx();
        wxapi.handleIntent(getIntent(), this);

        wxPresenter = new WXPresenterImpl();
        wxPresenter.onCreate();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void regToWx() {
        wxapi.registerApp(WeChatConfig.APP_ID);
    }

    /**
     * 微信发送的请求将回调到onReq方法
     *
     * @param baseReq
     */
    @Override
    public void onReq(BaseReq baseReq) {
        System.out.println(TAG + "  " + "onReq");
    }

    /**
     * 发送到微信请求的响应结果将回调到onResp方法
     *
     * @param baseResp
     */
    @Override
    public void onResp(BaseResp baseResp) {
        System.out.println(TAG + "  " + "onResp");
    }

}
