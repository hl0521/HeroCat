package me.uteacher.www.herocat.util.FileStorageHelper;

import java.io.File;

/**
 * Created by HL0521 on 2016/2/16.
 */
public interface IVideoFileHelper {

    /**
     * 获取指定视频文件
     *
     * @param fileName 待获取文件的 文件名
     * @return 返回文件
     */
    public File getVideoFile(String fileName);

    /**
     * 删除指定文件
     *
     * @param fileName 待删除文件的 文件名
     */
    public void deleteVideoFile(String fileName);

    /**
     * 删除所有的视频文件
     */
    public void deleteAllVideoFiles();

}
