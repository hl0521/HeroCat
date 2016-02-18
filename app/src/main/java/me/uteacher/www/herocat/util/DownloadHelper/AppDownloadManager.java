package me.uteacher.www.herocat.util.DownloadHelper;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.util.HashMap;

/**
 * Created by HL0521 on 2016/2/16.
 */
public final class AppDownloadManager {

    private static final String TAG = "AppDownloadManager";

    private static AppDownloadManager appDownloadManager;

    private DownloadManager downloadManager;
    private BroadcastReceiver broadcastReceiver;
    private HashMap<Long, OnDownloadCompleteListener> listenerHashMap;

    public AppDownloadManager() {

    }

    public static void init(Context context) {
        if (appDownloadManager == null) {
            appDownloadManager = new AppDownloadManager();
            appDownloadManager.initialize(context);
            appDownloadManager.register(context);
        }
    }

    /**
     * 使用之前需要先调用初始化 init 函数，否则返回的结果为空
     *
     * @return appDownloadManager
     */
    public static AppDownloadManager getInstance() {
        return appDownloadManager;
    }

    private void initialize(Context context) {
        downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        listenerHashMap = new HashMap<>();
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                long reference = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                OnDownloadCompleteListener listener = listenerHashMap.get(reference);
                if (listener != null) {
                    listenerHashMap.remove(reference);
                    listener.onComplete(reference);
                }
            }
        };
    }

    public void register(Context context) {
        IntentFilter intentFilter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        context.registerReceiver(broadcastReceiver, intentFilter);
    }

    public void unregister(Context context) {
        context.unregisterReceiver(broadcastReceiver);
        listenerHashMap.clear();
    }

    public DownloadManager getDownloadManager() {
        return downloadManager;
    }

    public long enqueueRequest(DownloadManager.Request request, OnDownloadCompleteListener listener) {
        long reference = downloadManager.enqueue(request);
        listenerHashMap.put(reference, listener);
        return reference;
    }

    public interface OnDownloadCompleteListener {

        public void onComplete(long reference);

    }

}
