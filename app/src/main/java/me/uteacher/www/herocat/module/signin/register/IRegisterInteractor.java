package me.uteacher.www.herocat.module.signin.register;

import me.uteacher.www.herocat.model.user.IUserModel;

/**
 * Created by HL0521 on 2016/1/29.
 */
public interface IRegisterInteractor {

    public void requestPhoneSmsCode(String phoneNumber, OnPhoneListener listener);

    public void verifyPhoneSmsCode(String phoneNumber, String smsCode, OnPhoneListener listener);

    public interface OnPhoneListener {

        public void onSuccess(String phone);

        public void onFailure(String error);

    }

    public void signupNewUser(String username, String password, String phoneNumber, OnSignupListener listener);

    public interface OnSignupListener {

        public void onSuccess(IUserModel userModel);

        public void onFailure(String error);

    }

}
