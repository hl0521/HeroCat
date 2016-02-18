package me.uteacher.www.herocat.util.FileStorageHelper;

import java.io.File;

/**
 * Created by HL0521 on 2016/2/16.
 */
public class VideoFileHelper implements IVideoFileHelper {

    private static final String TAG = "VideoFileHelper";

    private static final String DIRECTORY_VIDEO = "/video";

    private AppFileStorage appFileStorage;

    public VideoFileHelper(AppFileStorage appFileStorage) {
        this.appFileStorage = appFileStorage;

        File file = appFileStorage.getExternalFile(DIRECTORY_VIDEO);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    @Override
    public File getVideoFile(String fileName) {
        File file = appFileStorage.getExternalFile(DIRECTORY_VIDEO + "/" + fileName);
        return file;
    }

    @Override
    public void deleteVideoFile(String fileName) {
        getVideoFile(fileName).delete();
    }

    @Override
    public void deleteAllVideoFiles() {
        File file = appFileStorage.getExternalFile(DIRECTORY_VIDEO);
        if (file.exists() && file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                f.delete();
            }
        }
    }
}
