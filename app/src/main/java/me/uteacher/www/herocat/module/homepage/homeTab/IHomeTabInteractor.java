package me.uteacher.www.herocat.module.homepage.homeTab;


import java.io.InputStream;
import java.util.List;

import me.uteacher.www.herocat.model.instagram.IInstagramModel;

/**
 * Created by HL0521 on 2016/1/20.
 */
public interface IHomeTabInteractor {

    /**
     * 通过 组别 （Group） 来获取 InstagramModel 对象
     *
     * @param group    待获取的 组别
     * @param listener 回调函数
     */
    public void getInstagramItemsByGroup(String group, OnGetInstagramItemListener listener);

    /**
     * 通过 组别 （Group） 来获取 InstagramModel 对象
     *
     * @param limit    限制查询结果的数据条数
     * @param skip     跳过首次查询的 skip 条数据来实现分页的功能
     * @param group    待获取的 组别
     * @param listener 回调函数
     */
    public void getInstagramItemsByGroup(int limit, int skip, String group, OnGetInstagramItemListener listener);

    /**
     * 通过 受欢迎程度 来获取 InstagramModel 对象
     *
     * @param listener 回调函数
     */
    public void getInstagramItemsByPopularity(OnGetInstagramItemListener listener);

    /**
     * 通过 受欢迎程度 来获取 InstagramModel 对象
     *
     * @param limit    限制查询结果的数据条数
     * @param skip     跳过首次查询的 skip 条数据来实现分页的功能
     * @param listener 回调函数
     */
    public void getInstagramItemsByPopularity(int limit, int skip, OnGetInstagramItemListener listener);


    public interface OnGetInstagramItemListener {

        public void onSuccess(List<IInstagramModel> list);

        public void onFailure(String error);

    }

    /**
     * 获取图片的 InputStream
     *
     * @param url      图片的源地址
     * @param callback 回调函数
     */
    public void getImageStream(String url, OnGetImageStreamCallback callback);

    public interface OnGetImageStreamCallback {

        public void onSuccess(InputStream inputStream);

        public void onFailure(String error);

    }

    /**
     * 根据 InstagramModel ，查找所需的视频文件（如果本地存在，则读取本地文件的Uri，如果不存在，则从网络下载）
     *
     * @param instagramModel InstagramModel 数据
     * @param callback       回调函数
     */
    public void locateVideoUri(IInstagramModel instagramModel, onVideoUriLocatedCallback callback);

    public interface onVideoUriLocatedCallback {

        public void onSuccess(IInstagramModel instagramModel, String uri);

        public void onFailure(int error);

    }
}
