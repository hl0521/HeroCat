package me.uteacher.www.herocat.module.signin.register;

import me.uteacher.www.herocat.app.IPresenterLife;

/**
 * Created by HL0521 on 2016/1/29.
 */
public interface IRegisterPresenter extends IPresenterLife {

    public void onGetSmsCodeBtnClicked();

    public void onRegisterBtnClicked();

    public void onNavagateToLoginBtnClicked();

    public void onInputInfoChanged();

}
