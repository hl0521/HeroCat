package me.uteacher.www.herocat.util.DownloadHelper;

import android.app.DownloadManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import java.io.File;


/**
 * Created by HL0521 on 2016/2/16.
 */
public class VideoDownloadHelper implements IVideoDownloadHelper {

    private static final String TAG = "VideoDownloadHelper";

    private AppDownloadManager appDownloadManager;

    public VideoDownloadHelper(AppDownloadManager appDownloadManager) {
        this.appDownloadManager = appDownloadManager;
    }

    @Override
    public void download(Uri uri, final File file, final IDownloadCallback callback) {
        DownloadManager.Request request = new DownloadManager.Request(uri);
//        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setAllowedNetworkTypes(~0);
        request.setAllowedOverRoaming(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            request.setAllowedOverMetered(true);
        }
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN);
        request.setDestinationUri(Uri.fromFile(file));

        appDownloadManager.enqueueRequest(request, new AppDownloadManager.OnDownloadCompleteListener() {
            @Override
            public void onComplete(long reference) {
                DownloadManager.Query query = new DownloadManager.Query();
                query.setFilterById(reference);
                Log.d(TAG, "download completed : " + reference);
                Cursor cursor = appDownloadManager.getDownloadManager().query(query);

                if (cursor.moveToFirst()) {
                    int status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));
                    if (status == DownloadManager.STATUS_SUCCESSFUL) {
                        callback.onSuccess(file);
                    } else if (status == DownloadManager.STATUS_FAILED) {
                        int reason = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_REASON));
                        callback.onError(reason);
                    } else {

                    }
                }

                cursor.close();
            }
        });
    }

    @Override
    public void download(String url, File file, IDownloadCallback callback) {
        download(Uri.parse(url), file, callback);
    }
}
