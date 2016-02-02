package me.uteacher.www.herocat.dao.Proxy;


import me.uteacher.www.herocat.dao.BaseDAO;
import me.uteacher.www.herocat.dao.IDAODoneCallback;
import me.uteacher.www.herocat.dao.IDAOGetCallback;
import me.uteacher.www.herocat.model.user.IUserModel;

/**
 * Created by HL0521 on 2016/1/25.
 */
public class UserDAOProxy extends BaseDAO implements IUserDAO {

    private IUserDAO userDAO;

    public UserDAOProxy(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void getCurrentUser(IDAOGetCallback<IUserModel> callback) {
        userDAO.getCurrentUser(callback);
    }

    @Override
    public void logoutCurrentUser(IDAOGetCallback<IUserModel> callback) {
        userDAO.logoutCurrentUser(callback);
    }

    @Override
    public void loginThirdPartyUser(String usename, String token, String expires, String snsType
            , String openId, IDAOGetCallback<IUserModel> callback) {
        userDAO.loginThirdPartyUser(usename, token, expires, snsType, openId, callback);
    }

    @Override
    public void loginUser(String username, String password, IDAOGetCallback<IUserModel> callback) {
        userDAO.loginUser(username, password, callback);
    }

    @Override
    public void loginPhoneNumber(String phoneNumber, String password
            , IDAOGetCallback<IUserModel> callback) {
        userDAO.loginPhoneNumber(phoneNumber, password, callback);
    }

    @Override
    public void requestPhoneVerify(String phoneNumber, IDAODoneCallback callback) {
        userDAO.requestPhoneVerify(phoneNumber, callback);
    }

    @Override
    public void verifyPhone(String phoneNumber, String smsCode, IDAODoneCallback callback) {
        userDAO.verifyPhone(phoneNumber, smsCode, callback);
    }

    @Override
    public void signUpUser(String username, String password, String phoneNumber
            , IDAOGetCallback<IUserModel> callback) {
        userDAO.signUpUser(username, password, phoneNumber, callback);
    }
}
