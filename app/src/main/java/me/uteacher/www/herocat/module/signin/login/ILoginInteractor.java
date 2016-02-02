package me.uteacher.www.herocat.module.signin.login;

import me.uteacher.www.herocat.model.user.IUserModel;

/**
 * Created by HL0521 on 2016/1/29.
 */
public interface ILoginInteractor {

    public void loginUser(String username, String password, OnLoginListener listener);

    public void loginPhoneNumber(String phoneNumber, String password, OnLoginListener listener);

    public interface OnLoginListener {

        public void onSuccess(IUserModel userModel);

        public void onFailure(String error);

    }
}
