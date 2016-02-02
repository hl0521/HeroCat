package me.uteacher.www.herocat.module.main;

import me.uteacher.www.herocat.app.BaseInteractor;
import me.uteacher.www.herocat.dao.DAOException;
import me.uteacher.www.herocat.dao.DAOProxyFactory;
import me.uteacher.www.herocat.dao.IDAOGetCallback;
import me.uteacher.www.herocat.model.application.IApplicationModel;
import me.uteacher.www.herocat.model.user.IUserModel;

/**
 * Created by HL0521 on 2016/1/19.
 */
public class MainInteractorImpl extends BaseInteractor implements IMainInteractor {

    @Override
    public void getApplicationModel(final OnGetApplicationModelListener listener) {
        DAOProxyFactory.getApplicationDAO().getApplication(new IDAOGetCallback<IApplicationModel>() {
            @Override
            public void done(IApplicationModel item, DAOException e) {
                if (e == null) {
                    listener.onSuccess(item);
                } else {
                    listener.onFailure(e.getMessage());
                }
            }
        });
    }

    @Override
    public void getCurrentUser(final OnGetUserListener listener) {
        DAOProxyFactory.getUserDAO().getCurrentUser(new IDAOGetCallback<IUserModel>() {
            @Override
            public void done(IUserModel item, DAOException e) {
                if (e == null) {
                    listener.onSuccess(item);
                } else {
                    listener.onFailure(e.getMessage());
                }
            }
        });
    }

    @Override
    public void logoutCurrentUser(final OnLogoutListener listener) {
        DAOProxyFactory.getUserDAO().logoutCurrentUser(new IDAOGetCallback<IUserModel>() {
            @Override
            public void done(IUserModel item, DAOException e) {
                if (e == null) {
                    listener.onSuccess(item);
                } else {
                    listener.onFailure(e.getMessage());
                }
            }
        });
    }

}
