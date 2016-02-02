package me.uteacher.www.herocat.module.main;

import me.uteacher.www.herocat.model.application.IApplicationModel;
import me.uteacher.www.herocat.model.user.IUserModel;

/**
 * Created by HL0521 on 2016/1/19.
 */
public interface IMainInteractor {

    /**
     * 获取 app 的模型
     *
     * @param listener
     */
    public void getApplicationModel(OnGetApplicationModelListener listener);

    /**
     * 获取 app 模型的回调函数接口
     */
    public interface OnGetApplicationModelListener {

        public void onSuccess(IApplicationModel applicationModel);

        public void onFailure(String error);
    }

    /**
     * 获取当前用户
     *
     * @param listener 用来返回 当前用户 的回调函数
     */
    public void getCurrentUser(OnGetUserListener listener);

    public interface OnGetUserListener {

        public void onSuccess(IUserModel userModel);

        public void onFailure(String error);

    }

    /**
     * 注销当前用户
     *
     * @param listener 返回注销前的用户
     */
    public void logoutCurrentUser(OnLogoutListener listener);

    public interface OnLogoutListener {

        public void onSuccess(IUserModel preUserModel);

        public void onFailure(String error);

    }

//    public void loginThirdPartyUser();
}
