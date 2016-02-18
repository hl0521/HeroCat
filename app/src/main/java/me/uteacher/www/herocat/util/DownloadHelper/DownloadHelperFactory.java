package me.uteacher.www.herocat.util.DownloadHelper;

/**
 * Created by HL0521 on 2016/2/17.
 */
public class DownloadHelperFactory {

    private static IVideoDownloadHelper videoDownloadHelper;

    public static IVideoDownloadHelper getVideoDownloadHelper() {
        if (videoDownloadHelper == null) {
            videoDownloadHelper = new VideoDownloadHelper(AppDownloadManager.getInstance());
        }
        return videoDownloadHelper;
    }

}
