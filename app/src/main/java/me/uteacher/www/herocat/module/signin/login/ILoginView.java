package me.uteacher.www.herocat.module.signin.login;

import me.uteacher.www.herocat.app.IBaseView;
import me.uteacher.www.herocat.app.IDebug;
import me.uteacher.www.herocat.module.main.IMainView;

/**
 * Created by HL0521 on 2016/1/29.
 */
public interface ILoginView extends IBaseView, IDebug {

    public IMainView getMainView();

    public void setupToolbar();

    public void showThirdPartyEntry();

    public void loginThirdParty(String username, String token, String expires, String snsType, String openId);

    public void navigateToForgetPassword();

    public void navigateToRegister();

    public void setupLoginBtnState();

    public String getAcount();

    public String getPassword();
}
