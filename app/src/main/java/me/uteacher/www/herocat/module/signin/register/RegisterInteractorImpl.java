package me.uteacher.www.herocat.module.signin.register;

import me.uteacher.www.herocat.dao.DAOException;
import me.uteacher.www.herocat.dao.DAOProxyFactory;
import me.uteacher.www.herocat.dao.IDAODoneCallback;
import me.uteacher.www.herocat.dao.IDAOGetCallback;
import me.uteacher.www.herocat.model.user.IUserModel;

/**
 * Created by HL0521 on 2016/1/29.
 */
public class RegisterInteractorImpl implements IRegisterInteractor {

    @Override
    public void requestPhoneSmsCode(final String phoneNumber, final OnPhoneListener listener) {
        DAOProxyFactory.getUserDAO().requestPhoneVerify(phoneNumber, new IDAODoneCallback() {
            @Override
            public void done(DAOException e) {
                if (e == null) {
                    listener.onSuccess(phoneNumber);
                } else {
                    listener.onFailure(e.getMessage());
                }
            }
        });
    }

    @Override
    public void verifyPhoneSmsCode(final String phoneNumber, String smsCode, final OnPhoneListener listener) {
        DAOProxyFactory.getUserDAO().verifyPhone(phoneNumber, smsCode, new IDAODoneCallback() {
            @Override
            public void done(DAOException e) {
                if (e == null) {
                    listener.onSuccess(phoneNumber);
                } else {
                    listener.onFailure(e.getMessage());
                }
            }
        });
    }

    @Override
    public void signupNewUser(String username, String password, String phoneNumber, final OnSignupListener listener) {
        DAOProxyFactory.getUserDAO().signUpUser(username, password, phoneNumber, new IDAOGetCallback<IUserModel>() {
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
