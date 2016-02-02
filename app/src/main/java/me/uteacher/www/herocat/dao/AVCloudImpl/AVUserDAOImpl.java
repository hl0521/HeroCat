package me.uteacher.www.herocat.dao.AVCloudImpl;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVMobilePhoneVerifyCallback;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.RequestMobileCodeCallback;
import com.avos.avoscloud.SignUpCallback;

import me.uteacher.www.herocat.dao.BaseDAO;
import me.uteacher.www.herocat.dao.DAOException;
import me.uteacher.www.herocat.dao.IDAODoneCallback;
import me.uteacher.www.herocat.dao.IDAOGetCallback;
import me.uteacher.www.herocat.dao.Proxy.IUserDAO;
import me.uteacher.www.herocat.model.ModelFactory;
import me.uteacher.www.herocat.model.user.IUserModel;

/**
 * Created by HL0521 on 2016/1/25.
 */
public class AVUserDAOImpl extends BaseDAO implements IUserDAO {

    public AVUserDAOImpl() {

    }

    @Override
    public void getCurrentUser(IDAOGetCallback<IUserModel> callback) {
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            IUserModel userModel = ModelFactory.createUserModel(currentUser.toJSONObject().toString());
            callback.done(userModel, null);
        } else {
            callback.done(null, new DAOException("Current User is null"));
        }
    }

    @Override
    public void logoutCurrentUser(IDAOGetCallback<IUserModel> callback) {
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            IUserModel userModel = ModelFactory.createUserModel(currentUser.toJSONObject().toString());
            AVUser.logOut();
            callback.done(userModel, null);
        } else {
            callback.done(null, new DAOException("Current user is null"));
        }
    }

    @Override
    public void loginThirdPartyUser(String usename, String token, String expires, String snsType
            , String openId, IDAOGetCallback<IUserModel> callback) {
        // TODO: 2016/1/25 原理暂时没有深入研究，有待进一步地完善！
    }

    @Override
    public void loginUser(String username, String password, final IDAOGetCallback<IUserModel> callback) {
        AVUser.logInInBackground(username, password, new LogInCallback<AVUser>() {
            @Override
            public void done(AVUser user, AVException e) {
                if (e != null) {
                    IUserModel userModel = ModelFactory.createUserModel(user.toJSONObject().toString());
                    callback.done(userModel, null);
                } else {
                    callback.done(null, new DAOException(e.getMessage()));
                }
            }
        });
    }

    @Override
    public void loginPhoneNumber(String phoneNumber, String password, final IDAOGetCallback<IUserModel> callback) {
        AVUser.loginByMobilePhoneNumberInBackground(phoneNumber, password, new LogInCallback<AVUser>() {
            @Override
            public void done(AVUser user, AVException e) {
                if (e == null) {
                    IUserModel userModel = ModelFactory.createUserModel(user.toJSONObject().toString());
                    callback.done(userModel, null);
                } else {
                    callback.done(null, new DAOException(e.getMessage()));
                }
            }
        });
    }

    @Override
    public void requestPhoneVerify(String phoneNumber, final IDAODoneCallback callback) {
        AVOSCloud.requestSMSCodeInBackground(phoneNumber, new RequestMobileCodeCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    callback.done(null);
                } else {
                    callback.done(new DAOException(e.getMessage()));
                }
            }
        });
    }

    @Override
    public void verifyPhone(String phoneNumber, String smsCode, final IDAODoneCallback callback) {
        AVOSCloud.verifyCodeInBackground(smsCode, phoneNumber, new AVMobilePhoneVerifyCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    callback.done(null);
                } else {
                    callback.done(new DAOException(e.getMessage()));
                }
            }
        });
    }

    @Override
    public void signUpUser(String username, String password, String phoneNumber
            , final IDAOGetCallback<IUserModel> callback) {
        AVUser avUser = new AVUser();
        avUser.setUsername(username);
        avUser.setPassword(password);
        avUser.setMobilePhoneNumber(phoneNumber);

        avUser.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    getCurrentUser(callback);
                } else {
                    callback.done(null, new DAOException(e.getMessage()));
                }
            }
        });
    }
}
