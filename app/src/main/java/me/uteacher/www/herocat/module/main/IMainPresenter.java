package me.uteacher.www.herocat.module.main;

import me.uteacher.www.herocat.app.IPresenterLife;

/**
 * Created by HL0521 on 2016/1/19.
 */
public interface IMainPresenter extends IPresenterLife {

    /**
     * 侧边栏菜单被选择后，相应的响应函数
     */
    public void onNavigationSigninSelected();

    public void onNavigationHomepageSelected();

    public void onNavigationFavouriteSelected();

    public void onNavigationSettingSelected();

    public void onNavigationQuitSelected();

    /**
     * 当前用户被更改时，作出相应的处理
     */
    public void onSetUserModel();

    /**
     * 当某些操作需要登陆时，调用此函数
     */
    public void onActionNeedLogin();

}
