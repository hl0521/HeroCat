package me.uteacher.www.herocat.module.signin.register;

import me.uteacher.www.herocat.app.IBaseView;
import me.uteacher.www.herocat.app.IDebug;
import me.uteacher.www.herocat.module.main.IMainView;

/**
 * Created by HL0521 on 2016/1/29.
 */
public interface IRegisterView extends IBaseView, IDebug {

    public void setupToolbar();

    public IMainView getMainView();

    public void navigateToLogin();

    public void setupRegisterBtnState();

    public void setupGetSmsBtnState();

    public String getAccountName();

    public String getSmsCode();

    public String getPassword1();

    public String getPassword2();

}
