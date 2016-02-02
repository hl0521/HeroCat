package me.uteacher.www.herocat.dao.Proxy;

import me.uteacher.www.herocat.dao.BaseDAO;
import me.uteacher.www.herocat.dao.IDAODoneCallback;
import me.uteacher.www.herocat.dao.IDAOQueryCallback;
import me.uteacher.www.herocat.model.instagram.IInstagramModel;

/**
 * Created by HL0521 on 2016/1/25.
 */
public class InstagramDAOProxy extends BaseDAO implements IInstagramDAO {

    private IInstagramDAO instagramDAO;

    public InstagramDAOProxy(IInstagramDAO instagramDAO) {
        this.instagramDAO = instagramDAO;
    }

    @Override
    public void userLikeInstagramItem(String itemId, String userId, IDAODoneCallback callback) {
        instagramDAO.userLikeInstagramItem(itemId, userId, callback);
    }

    @Override
    public void userCancelLikeInstagramItem(String itemId, String userId, IDAODoneCallback callback) {
        instagramDAO.userCancelLikeInstagramItem(itemId, userId, callback);
    }

    @Override
    public void userFavouriteInstagramItem(String item, String userId, IDAODoneCallback callback) {
        instagramDAO.userFavouriteInstagramItem(item, userId, callback);
    }

    @Override
    public void userCancelFavouriteInstagramItem(String item, String userId, IDAODoneCallback callback) {
        instagramDAO.userCancelFavouriteInstagramItem(item, userId, callback);
    }

    @Override
    public void queryInstagramItemsByGroup(String group, int limit, int skip, IDAOQueryCallback<IInstagramModel> callback) {
        instagramDAO.queryInstagramItemsByGroup(group, limit, skip, callback);
    }

    @Override
    public void queryInstagramItemsByPopularity(int limit, int skip, IDAOQueryCallback<IInstagramModel> callback) {
        instagramDAO.queryInstagramItemsByPopularity(limit, skip, callback);
    }

    @Override
    public void queryUserFavouriteItems(String userId, int limit, int skip, IDAOQueryCallback<IInstagramModel> callback) {
        instagramDAO.queryUserFavouriteItems(userId, limit, skip, callback);
    }
}
