package me.uteacher.www.herocat.model.instagram;

import android.os.Parcelable;

import me.uteacher.www.herocat.model.user.IUserModel;

/**
 * Created by HL0521 on 2016/1/21.
 */
public interface IInstagramModel extends Parcelable {

    public InstagramBean getInstagramBean();

    /**
     * 添加喜欢的 用户
     *
     * @param userId 用户在 LeanCloud 上的 userId
     */
    public void addLikedUser(String userId);

    /**
     * 添加收藏的 用户
     *
     * @param userId 用户在 LeanCloud 上的 userId
     */
    public void addStoredUser(String userId);

    /**
     * 用户是否喜欢该 InstagramModel
     *
     * @param user
     * @return 是：true；否：false
     */
    public boolean isUserLiked(IUserModel user);

    /**
     * 用户是否收藏了该 InstagramModel
     *
     * @param user
     * @return 是：true；否：false
     */
    public boolean isUserStored(IUserModel user);

    /**
     * 用户 点击了喜欢该 InstagramModel
     *
     * @param user
     */
    public void userLikes(IUserModel user);

    /**
     * 用户 取消了喜欢该 InstagramModel
     *
     * @param user
     */
    public void userCancelLike(IUserModel user);

    /**
     * 用户 点击了收藏该 InstagramModel
     *
     * @param user
     */
    public void userFavourite(IUserModel user);

    /**
     * 用户 取消了收藏该 InstagramModel
     *
     * @param user
     */
    public void userCancelFavourite(IUserModel user);

}
