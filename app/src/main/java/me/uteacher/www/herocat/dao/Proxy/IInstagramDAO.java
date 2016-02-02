package me.uteacher.www.herocat.dao.Proxy;

import me.uteacher.www.herocat.dao.IDAODoneCallback;
import me.uteacher.www.herocat.dao.IDAOQueryCallback;
import me.uteacher.www.herocat.model.instagram.IInstagramModel;

/**
 * Created by HL0521 on 2016/1/24.
 */
public interface IInstagramDAO {

    /**
     * 用户点击了喜欢 Instagram 图片/视频
     *
     * @param itemId   InstagramModel 的 ObjectId
     * @param userId   UserModel 的 ObjectId
     * @param callback 回调函数
     */
    public void userLikeInstagramItem(String itemId, String userId, IDAODoneCallback callback);

    /**
     * 用户点击了取消喜欢 InstagramModel 图片/视频
     *
     * @param itemId   InstagramModel 的 ObjectId
     * @param userId   UserModel 的 ObjectId
     * @param callback 回调函数
     */
    public void userCancelLikeInstagramItem(String itemId, String userId, IDAODoneCallback callback);

    /**
     * 用户点击了收藏 InstagramModel 图片/视频
     *
     * @param itemId   InstagramModel 的 ObjectId
     * @param userId   UserModel 的 ObjectId
     * @param callback 回调函数
     */
    public void userFavouriteInstagramItem(String itemId, String userId, IDAODoneCallback callback);

    /**
     * 用户点击了取消收藏 InstagramModel 图片/视频
     *
     * @param itemId   InstagramModel 的 ObjectId
     * @param userId   UserModel 的 ObjectId
     * @param callback 回调函数
     */
    public void userCancelFavouriteInstagramItem(String itemId, String userId, IDAODoneCallback callback);

    /**
     * 根据 组别 （Group） 从云端查询数据并返回
     *
     * @param group    待查询的数据的组别
     * @param limit    一次最多查询 limit 条数据
     * @param skip     跳过开头的 skip 条数据
     * @param callback 回调函数
     */
    public void queryInstagramItemsByGroup(String group, int limit, int skip, IDAOQueryCallback<IInstagramModel> callback);

    /**
     * 根据 流行性 （用户点击的喜欢数量排行） 从云端查询数据并返回
     *
     * @param limit    一次最多查询 limit 条数据
     * @param skip     跳过开头的 skip 条数据
     * @param callback 回调函数
     */
    public void queryInstagramItemsByPopularity(int limit, int skip, IDAOQueryCallback<IInstagramModel> callback);

    /**
     * 查询特定用户喜欢的数据
     *
     * @param userId   UserModel 的 ObjectId
     * @param limit    一次最多查询 limit 条数据
     * @param skip     跳过开头的 skip 条数据
     * @param callback 回调函数
     */
    public void queryUserFavouriteItems(String userId, int limit, int skip, IDAOQueryCallback<IInstagramModel> callback);
}
