package me.uteacher.www.herocat.module.signin.login;

import android.widget.EditText;

import me.uteacher.www.herocat.R;
import me.uteacher.www.herocat.model.user.IUserModel;
import me.uteacher.www.herocat.util.Telephone;

/**
 * Created by HL0521 on 2016/1/29.
 */
public class LoginPresenterImpl implements ILoginPresenter {

    private ILoginView loginView;
    private ILoginInteractor loginInteractor;

    public LoginPresenterImpl(ILoginView loginView) {
        this.loginView = loginView;
        if (loginInteractor == null) {
            loginInteractor = new LoginInteractorImpl();
        }
    }

    @Override
    public void onCreate() {
        loginView.initContentView();
        loginView.setupToolbar();
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        loginView = null;
        loginInteractor = null;
    }

    @Override
    public void onLoginClicked() {
        if (Telephone.getInstance().isTepephoneNumber(loginView.getAcount())) {
            loginInteractor.loginPhoneNumber(loginView.getAcount(), loginView.getPassword()
                    , new ILoginInteractor.OnLoginListener() {
                @Override
                public void onSuccess(IUserModel userModel) {
                    loginView.getMainView().setUserModel(userModel);
                    loginView.getMainView().navigateToHomepage();
                    loginView.getMainView().showMessage("登陆成功");
                    loginView.getMainView().setLoginMenuText(R.string.navigation_menu_signout);
                }

                @Override
                public void onFailure(String error) {
                    loginView.getMainView().showMessage(error);
                }
            });
        } else {
            loginInteractor.loginUser(loginView.getAcount(), loginView.getPassword()
                    , new ILoginInteractor.OnLoginListener() {
                @Override
                public void onSuccess(IUserModel userModel) {
                    loginView.getMainView().setUserModel(userModel);
                    loginView.getMainView().navigateToHomepage();
                    loginView.getMainView().showMessage("登陆成功");
                    loginView.getMainView().setLoginMenuText(R.string.navigation_menu_signout);
                }

                @Override
                public void onFailure(String error) {
                    loginView.getMainView().showMessage(error);
                }
            });
        }
    }

    @Override
    public void onLoginProblemClicked() {
        loginView.navigateToForgetPassword();
    }

    @Override
    public void onLoginRegisterClicked() {
        loginView.navigateToRegister();
    }

    @Override
    public void onLoginThirdPartyClicked() {

    }

    @Override
    public void onInputInfoChanged() {
        loginView.setupLoginBtnState();
    }

}
