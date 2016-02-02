package me.uteacher.www.herocat.module.signin.register;

import me.uteacher.www.herocat.model.user.IUserModel;
import me.uteacher.www.herocat.util.Telephone;

/**
 * Created by HL0521 on 2016/1/29.
 */
public class RegisterPresenterImpl implements IRegisterPresenter {

    private static final String TAG = "RegisterPresenterImpl";

    private IRegisterView registerView;
    private IRegisterInteractor registerInteractor;

    public RegisterPresenterImpl(IRegisterView registerView) {
        this.registerView = registerView;
        if (registerInteractor == null) {
            registerInteractor = new RegisterInteractorImpl();
        }
    }

    @Override
    public void onGetSmsCodeBtnClicked() {
        String phoneNumber = registerView.getAccountName();
        if (Telephone.getInstance().isTepephoneNumber(phoneNumber)) {
            registerInteractor.requestPhoneSmsCode(phoneNumber, new IRegisterInteractor.OnPhoneListener() {
                @Override
                public void onSuccess(String phone) {

                }

                @Override
                public void onFailure(String error) {
                    registerView.getMainView().showMessage(error);
                }
            });
        } else {
            registerView.getMainView().showMessage("手机号码输入错误！");
        }
    }

    @Override
    public void onRegisterBtnClicked() {
        final String password1 = registerView.getPassword1();
        String password2 = registerView.getPassword2();

        if (!password1.equals(password2)) {
            registerView.getMainView().showMessage("密码不一致，请重新输入");
            return;
        }

        registerInteractor.verifyPhoneSmsCode(registerView.getAccountName(), registerView.getSmsCode()
                , new IRegisterInteractor.OnPhoneListener() {
            @Override
            public void onSuccess(String phone) {
                System.out.println(phone);
                registerInteractor.signupNewUser(phone, password1, phone
                        , new IRegisterInteractor.OnSignupListener() {
                    @Override
                    public void onSuccess(IUserModel userModel) {
                        registerView.getMainView().setUserModel(userModel);
                        registerView.getMainView().showMessage("注册成功！");
                        registerView.getMainView().navigateToHomepage();
                    }

                    @Override
                    public void onFailure(String error) {
                        registerView.getMainView().showMessage(error);
                    }
                });
            }

            @Override
            public void onFailure(String error) {
                registerView.getMainView().showMessage(error);
            }
        });
    }

    @Override
    public void onNavagateToLoginBtnClicked() {
        registerView.navigateToLogin();
    }

    @Override
    public void onInputInfoChanged() {
        registerView.setupGetSmsBtnState();
        registerView.setupRegisterBtnState();
    }

    @Override
    public void onCreate() {
        registerView.initContentView();
        registerView.setupToolbar();
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        registerView = null;
        registerInteractor = null;
    }
}
