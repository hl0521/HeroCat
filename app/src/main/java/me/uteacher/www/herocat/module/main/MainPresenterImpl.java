package me.uteacher.www.herocat.module.main;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.NonWritableChannelException;

import me.uteacher.www.herocat.app.BasePresenter;
import me.uteacher.www.herocat.app.IPresenterLife;
import me.uteacher.www.herocat.config.DefaultAppConfig;
import me.uteacher.www.herocat.model.application.IApplicationModel;
import me.uteacher.www.herocat.model.user.IUserModel;

/**
 * Created by HL0521 on 2016/1/19.
 */
public class MainPresenterImpl extends BasePresenter implements IMainPresenter {

    private static final String TAG = "MainPresenterImpl";

    private IMainView mainView;
    private IMainInteractor mainInteractor;

    public MainPresenterImpl(IMainView mainView) {
        this.mainView = mainView;
        this.mainInteractor = new MainInteractorImpl();
    }

    @Override
    public void onCreate() {
        if (mainView != null) {
            mainView.initContentView();

            mainInteractor.getCurrentUser(new IMainInteractor.OnGetUserListener() {
                @Override
                public void onSuccess(IUserModel userModel) {
                    mainView.setUserModel(userModel);
                }

                @Override
                public void onFailure(String error) {
                    mainView.setUserModel(null);
                    mainView.showMessage(error);
                }
            });

            mainInteractor.getApplicationModel(new IMainInteractor.OnGetApplicationModelListener() {
                @Override
                public void onSuccess(IApplicationModel applicationModel) {
                    mainView.setApplicationModel(applicationModel);

                    // TODO: 2016/1/26 读取的 ApplicationModel 数据应该缓存起来，下面这段代码应该可以制作成一个工具类！
                    if (!DefaultAppConfig.TEXT_FILE.exists()) {
                        DefaultAppConfig.TEXT_FILE.mkdirs();
                    }
                    File file = new File(DefaultAppConfig.TEXT_FILE, "ApplicationModel.txt");

                    String stringToFile = JSONObject.toJSONString(applicationModel.getApplicationBean());

                    BufferedWriter bufferedWriter = null;
                    try {
                        bufferedWriter = new BufferedWriter(new FileWriter(file), 1024);
                        bufferedWriter.write(stringToFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (bufferedWriter != null) {
                            try {
                                bufferedWriter.flush();
                                bufferedWriter.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

                @Override
                public void onFailure(String error) {
                    Log.d(TAG, error);
                    // TODO: 2016/1/26 这个地方应该缓存 ApplicationModel 数据，如果从网络获取失败，就应该从缓存中读取数据！
                }
            });
            mainView.navigateToHomepage();
        }
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onNavigationSigninSelected() {
        mainView.closeDrawer();
        if (mainView.getUserModel() != null) {
            mainView.showLogoutDialog("注销", "确认注销当前用户吗？", "确认", "取消", new IDialogCallback() {
                @Override
                public void onPositiveBtnClicked() {
                    mainInteractor.logoutCurrentUser(new IMainInteractor.OnLogoutListener() {
                        @Override
                        public void onSuccess(IUserModel preUserModel) {
                            mainView.setUserModel(null);
                            mainView.showMessage("注销成功!");
                        }

                        @Override
                        public void onFailure(String error) {
                            mainView.showMessage(error);
                        }
                    });
                }

                @Override
                public void onNegativeBtnClicked() {

                }

            });
        } else {
            mainView.navigateToSignin();
        }
    }

    @Override
    public void onNavigationHomepageSelected() {
        mainView.closeDrawer();
        mainView.navigateToHomepage();
    }

    @Override
    public void onNavigationFavouriteSelected() {
        mainView.closeDrawer();
        mainView.navigateToFavourite();
    }

    @Override
    public void onNavigationSettingSelected() {
        mainView.closeDrawer();
        mainView.navigateToSetting();
    }

    @Override
    public void onNavigationQuitSelected() {
        mainView.closeDrawer();
        mainView.nagigateToQuit();
    }

    @Override
    public void onSetUserModel() {
        IUserModel userModel = mainView.getUserModel();
        if (userModel != null) {
            mainView.setUserName(userModel.getUserBean().getUserName());
            mainView.setLoginMenuText("注销");
        } else {
            mainView.setUserName("英雄猫");
            mainView.setLoginMenuText("登陆");
        }
    }

    @Override
    public void onActionNeedLogin() {
        mainView.showNeedLoginDialog("登陆！", "该操作需要用户登陆", "登陆", "取消", new IDialogCallback() {
            @Override
            public void onPositiveBtnClicked() {
                mainView.navigateToSignin();
            }

            @Override
            public void onNegativeBtnClicked() {

            }

        });
    }

}
