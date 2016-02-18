package me.uteacher.www.herocat.util.DownloadHelper;

import android.net.Uri;

import java.io.File;

/**
 * Created by HL0521 on 2016/2/16.
 */
public interface IVideoDownloadHelper {

    public interface IDownloadCallback {

        public void onError(int error);

        public void onSuccess(File file);

    }

    public void download(Uri uri, File file, IDownloadCallback callback);

    public void download(String url, File file, IDownloadCallback callback);

}
