package me.uteacher.www.herocat.module.homepage.homeTab.recyclerViewAdapter;

import me.uteacher.www.herocat.model.instagram.IInstagramModel;
import me.uteacher.www.herocat.model.user.IUserModel;

/**
 * Created by HL0521 on 2016/1/21.
 */
public interface ICardInteractor {

    /**
     * 用户喜欢当前 Instagram 内容，点击了 喜欢 按钮
     *
     * @param instagramModel 当前的 InstagramModel
     * @param userModel      当前的 用户
     * @param callback       回调函数
     */
    public void userLikeItem(IInstagramModel instagramModel, IUserModel userModel, OnActionDoneCallback callback);

    /**
     * 用户取消喜欢当前 Instagram 内容，点击了 取消喜欢 按钮
     *
     * @param instagramModel 当前的 InstagramModel
     * @param userModel      当前的 用户
     * @param callback       回调函数
     */
    public void userCancelLikeItem(IInstagramModel instagramModel, IUserModel userModel, OnActionDoneCallback callback);

    /**
     * 用户收藏了当前 Instagram 内容，点击了 收藏 按钮
     *
     * @param instagramModel 当前的 InstagramModel
     * @param userModel      当前的 用户
     * @param callback       回调函数
     */
    public void userFavouriteItem(IInstagramModel instagramModel, IUserModel userModel, OnActionDoneCallback callback);

    /**
     * 用户取消收藏当前 Instagram 内容，点击了 取消收藏 按钮
     *
     * @param instagramModel 当前的 InstagramModel
     * @param userModel      当前的 用户
     * @param callback       回调函数
     */
    public void userCancelFavouriteItem(IInstagramModel instagramModel, IUserModel userModel, OnActionDoneCallback callback);

    public interface OnActionDoneCallback {

        public void onSuccess(IUserModel userModel, IInstagramModel item);

        public void onFailure(String error);
    }

}
