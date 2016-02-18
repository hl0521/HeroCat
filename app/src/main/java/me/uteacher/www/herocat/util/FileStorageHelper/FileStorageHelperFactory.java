package me.uteacher.www.herocat.util.FileStorageHelper;

/**
 * Created by HL0521 on 2016/2/16.
 */
public class FileStorageHelperFactory {

    private static IVideoFileHelper videoFileHelper;

    public static IVideoFileHelper getVideoFileHelper() {
        if (videoFileHelper == null) {
            videoFileHelper = new VideoFileHelper(AppFileStorage.getInstance());
        }
        return videoFileHelper;
    }

}
