package me.uteacher.www.herocat.app;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;
import com.facebook.drawee.backends.pipeline.Fresco;

import me.uteacher.www.herocat.config.AVOSCloudConfig;
import me.uteacher.www.herocat.util.DownloadHelper.AppDownloadManager;
import me.uteacher.www.herocat.util.FileStorageHelper.AppFileStorage;

/**
 * Created by HL0521 on 2016/1/21.
 */
public class MainApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        // Facebook 出版的 Fresco 初始化
        Fresco.initialize(getApplicationContext());
        // AVCloud 初始化
        AVOSCloud.initialize(this, AVOSCloudConfig.APP_ID, AVOSCloudConfig.APP_KEY);

        // AppDownloadManager 初始化
        AppDownloadManager.init(this);
        // AppFileStorage 初始化
        AppFileStorage.init(this);

    }


}
