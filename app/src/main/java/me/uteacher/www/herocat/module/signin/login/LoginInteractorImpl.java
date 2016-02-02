package me.uteacher.www.herocat.module.signin.login;

import me.uteacher.www.herocat.dao.DAOException;
import me.uteacher.www.herocat.dao.DAOProxyFactory;
import me.uteacher.www.herocat.dao.IDAOGetCallback;
import me.uteacher.www.herocat.model.user.IUserModel;

/**
 * Created by HL0521 on 2016/1/29.
 */
public class LoginInteractorImpl implements ILoginInteractor {

    @Override
    public void loginUser(String username, String password, final OnLoginListener listener) {
        DAOProxyFactory.getUserDAO().loginUser(username, password, new IDAOGetCallback<IUserModel>() {
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
    public void loginPhoneNumber(String phoneNumber, String password, final OnLoginListener listener) {
        DAOProxyFactory.getUserDAO().loginPhoneNumber(phoneNumber, password, new IDAOGetCallback<IUserModel>() {
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
