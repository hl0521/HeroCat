package me.uteacher.www.herocat.dao.Proxy;

import me.uteacher.www.herocat.dao.IDAODoneCallback;
import me.uteacher.www.herocat.dao.IDAOGetCallback;
import me.uteacher.www.herocat.model.user.IUserModel;

/**
 * Created by HL0521 on 2016/1/24.
 */
public interface IUserDAO {

    public void getCurrentUser(IDAOGetCallback<IUserModel> callback);

    public void logoutCurrentUser(IDAOGetCallback<IUserModel> callback);

    public void loginThirdPartyUser(String usename, String token, String expires, String snsType
            , String openId, IDAOGetCallback<IUserModel> callback);

    public void loginUser(String username, String password, IDAOGetCallback<IUserModel> callback);

    public void loginPhoneNumber(String phoneNumber, String password, IDAOGetCallback<IUserModel> callback);

    public void requestPhoneVerify(String phoneNumber, IDAODoneCallback callback);

    public void verifyPhone(String phoneNumber, String smsCode, IDAODoneCallback callback);

    public void signUpUser(String username, String password, String phoneNumber
            , IDAOGetCallback<IUserModel> callback);
}
