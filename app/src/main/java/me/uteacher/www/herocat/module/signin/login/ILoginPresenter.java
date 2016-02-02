package me.uteacher.www.herocat.module.signin.login;

import android.widget.EditText;

import me.uteacher.www.herocat.app.IPresenterLife;

/**
 * Created by HL0521 on 2016/1/29.
 */
public interface ILoginPresenter extends IPresenterLife {

    public void onLoginClicked();

    public void onLoginProblemClicked();

    public void onLoginRegisterClicked();

    public void onLoginThirdPartyClicked();

    public void onInputInfoChanged();

}
