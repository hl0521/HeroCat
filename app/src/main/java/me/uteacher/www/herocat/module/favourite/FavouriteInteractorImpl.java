package me.uteacher.www.herocat.module.favourite;

import java.util.List;

import me.uteacher.www.herocat.app.BaseInteractor;
import me.uteacher.www.herocat.config.DefaultAppConfig;
import me.uteacher.www.herocat.dao.DAOException;
import me.uteacher.www.herocat.dao.DAOProxyFactory;
import me.uteacher.www.herocat.dao.IDAOQueryCallback;
import me.uteacher.www.herocat.model.instagram.IInstagramModel;
import me.uteacher.www.herocat.model.user.IUserModel;

/**
 * Created by HL0521 on 2016/2/1.
 */
public class FavouriteInteractorImpl extends BaseInteractor implements IFavouriteInteractor {

    @Override
    public void getUserFavouriteItems(final IUserModel userModel, final OnGetUserFavouriteItemsListener listener) {
        DAOProxyFactory.getInstagramDAO().queryUserFavouriteItems(userModel.getUserBean().getObjectId()
                , DefaultAppConfig.DEFAULT_ITEM_LIMIT
                , DefaultAppConfig.DEFAULT_ITEM_SKIP
                , new IDAOQueryCallback<IInstagramModel>() {
            @Override
            public void done(List<IInstagramModel> items, DAOException e) {
                if (e == null) {
                    listener.onSuccess(userModel, items);
                } else {
                    listener.onFailure(e.getMessage());
                }
            }
        });
    }

    @Override
    public void getUserFavouriteItems(final IUserModel userModel, int limit, int skip, final OnGetUserFavouriteItemsListener listener) {
        DAOProxyFactory.getInstagramDAO().queryUserFavouriteItems(userModel.getUserBean().getObjectId()
                , limit, skip, new IDAOQueryCallback<IInstagramModel>() {
            @Override
            public void done(List<IInstagramModel> items, DAOException e) {
                if (e == null) {
                    listener.onSuccess(userModel, items);
                } else {
                    listener.onFailure(e.getMessage());
                }
            }
        });
    }

}
