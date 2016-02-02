package me.uteacher.www.herocat.config;

import android.os.Environment;

import java.io.File;

/**
 * Created by HL0521 on 2016/1/25.
 */
public class DefaultAppConfig {

    public static final int DEFAULT_ITEM_LIMIT = 20;
    public static final int DEFAULT_ITEM_SKIP = 0;

    public static final String VERSION_STRING = "v1.0.0";
    public static final int VERSION_INT = 0;

    // 文件存储根目录
    public static final File APP_FILE = new File(Environment.getExternalStorageDirectory(), "HeroCat");

    // ApplicationModel 存储目录
    public static final File TEXT_FILE = new File(Environment.getExternalStorageDirectory(), "HeroCat/text");
}
