package me.uteacher.www.herocat.module.homepage.homeTab.recyclerViewAdapter;

import me.uteacher.www.herocat.dao.DAOException;
import me.uteacher.www.herocat.dao.DAOProxyFactory;
import me.uteacher.www.herocat.dao.IDAODoneCallback;
import me.uteacher.www.herocat.model.instagram.IInstagramModel;
import me.uteacher.www.herocat.model.user.IUserModel;

/**
 * Created by HL0521 on 2016/1/21.
 */
public class CardInteractorImpl implements ICardInteractor {

    @Override
    public void userLikeItem(final IInstagramModel instagramModel, final IUserModel userModel
            , final OnActionDoneCallback callback) {
        DAOProxyFactory.getInstagramDAO().userLikeInstagramItem(instagramModel.getInstagramBean().getObjectId()
                , userModel.getUserBean().getObjectId(), new IDAODoneCallback() {
            @Override
            public void done(DAOException e) {
                if (e == null) {
                    callback.onSuccess(userModel, instagramModel);
                } else {
                    callback.onFailure(e.getMessage());
                }
            }
        });
    }

    @Override
    public void userCancelLikeItem(final IInstagramModel instagramModel, final IUserModel userModel
            , final OnActionDoneCallback callback) {
        DAOProxyFactory.getInstagramDAO().userCancelLikeInstagramItem(instagramModel.getInstagramBean().getObjectId()
                , userModel.getUserBean().getObjectId(), new IDAODoneCallback() {
            @Override
            public void done(DAOException e) {
                if (e == null) {
                    callback.onSuccess(userModel, instagramModel);
                } else {
                    callback.onFailure(e.getMessage());
                }
            }
        });
    }

    @Override
    public void userFavouriteItem(final IInstagramModel instagramModel, final IUserModel userModel
            , final OnActionDoneCallback callback) {
        DAOProxyFactory.getInstagramDAO().userFavouriteInstagramItem(instagramModel.getInstagramBean().getObjectId()
                , userModel.getUserBean().getObjectId(), new IDAODoneCallback() {
            @Override
            public void done(DAOException e) {
                if (e == null) {
                    callback.onSuccess(userModel, instagramModel);
                } else {
                    callback.onFailure(e.getMessage());
                }
            }
        });
    }

    @Override
    public void userCancelFavouriteItem(final IInstagramModel instagramModel, final IUserModel userModel
            , final OnActionDoneCallback callback) {
        DAOProxyFactory.getInstagramDAO().userCancelFavouriteInstagramItem(instagramModel.getInstagramBean().getObjectId()
                , userModel.getUserBean().getObjectId(), new IDAODoneCallback() {
            @Override
            public void done(DAOException e) {
                if (e == null) {
                    callback.onSuccess(userModel, instagramModel);
                } else {
                    callback.onFailure(e.getMessage());
                }
            }
        });
    }

}
