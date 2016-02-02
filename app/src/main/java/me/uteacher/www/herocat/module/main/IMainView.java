package me.uteacher.www.herocat.module.main;

import android.graphics.Bitmap;
import android.net.Uri;

import me.uteacher.www.herocat.app.IBaseView;
import me.uteacher.www.herocat.app.IDebug;
import me.uteacher.www.herocat.model.application.IApplicationModel;
import me.uteacher.www.herocat.model.user.IUserModel;

/**
 * Created by HL0521 on 2016/1/19.
 */
public interface IMainView extends IBaseView, IDebug {

    /**
     * 侧边栏的打开与关闭
     */
    public void openDrawer();

    public void closeDrawer();

    /**
     * 侧边栏点击后跳转到相应的页面
     */
    public void navigateToSignin();

    public void navigateToHomepage();

    public void navigateToFavourite();

    public void navigateToSetting();

    public void nagigateToQuit();

    /**
     * 设置 用户头像 和 用户名
     */
    public void setUserPortrait(Bitmap bitmap);

    public void setUserPortrait(String uri);

    public void setUserPortrait(int resId);

    public void setUserName(String userName);

    /**
     * 用户账户设置
     *
     * @param userModel
     */
    public void setUserModel(IUserModel userModel);

    public IUserModel getUserModel();

    /**
     * 设置 和 获取 ApplicationModel
     *
     * @param applicationModel
     */
    public void setApplicationModel(IApplicationModel applicationModel);

    public IApplicationModel getApplicationModel();

    /**
     * 设置 Drawer 中 Menu 下，登陆/注销 显示切换
     *
     * @param text
     */
    public void setLoginMenuText(String text);

    /**
     * 设置 Drawer 中 Menu 下，登陆/注销 显示切换
     *
     * @param id The resource id of the new text to be displayed.
     */
    public void setLoginMenuText(int id);

    /**
     * 弹出 需要登陆 Dialog
     *
     * @param title          Dialog 的标题
     * @param message        Dialog 中显示的详细信息
     * @param positive       Dialog 中 Positive 按钮显示的内容
     * @param negative       Dialog 中 Negative 按钮显示的内容
     * @param dialogCallback 回调函数
     */
    public void showNeedLoginDialog(String title, String message, String positive, String negative
            , IDialogCallback dialogCallback);

    /**
     * 弹出 注销 Dialog
     *
     * @param title          Dialog 的标题
     * @param message        Dialog 中显示的详细信息
     * @param positive       Dialog 中 Positive 按钮显示的内容
     * @param negative       Dialog 中 Negative 按钮显示的内容
     * @param dialogCallback 回调函数
     */
    public void showLogoutDialog(String title, String message, String positive, String negative
            , IDialogCallback dialogCallback);

}
