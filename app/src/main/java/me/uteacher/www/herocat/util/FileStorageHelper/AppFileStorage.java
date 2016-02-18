package me.uteacher.www.herocat.util.FileStorageHelper;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by HL0521 on 2016/2/16.
 */
public final class AppFileStorage {

    private static AppFileStorage appFileStorage;

    private final Context context;

    public AppFileStorage(Context context) {
        this.context = context;
    }

    public static AppFileStorage getInstance() {
        return appFileStorage;
    }

    public static void init(Context context) {
        if (appFileStorage == null) {
            appFileStorage = new AppFileStorage(context);
        }
    }

    public File getInternalFile(String fileName) {
        File file = new File(context.getFilesDir(), fileName);
        return file;
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    public File getExternalFile(String fileName) {
        File file = new File(context.getExternalFilesDir(null), fileName);
        return file;
    }
}
