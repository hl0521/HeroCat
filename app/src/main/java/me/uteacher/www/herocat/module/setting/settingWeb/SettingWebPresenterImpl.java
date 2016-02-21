package me.uteacher.www.herocat.module.setting.settingWeb;

import me.uteacher.www.herocat.app.BasePresenter;

/**
 * Created by HL0521 on 2016/2/19.
 */
public class SettingWebPresenterImpl extends BasePresenter implements ISettingWebPresenter {

    private ISettingWebView settingWebView;
    private ISettingWebInteractor settingWebInteractor;

    public SettingWebPresenterImpl(ISettingWebView settingWebView) {
        this.settingWebView = settingWebView;
    }

    @Override
    public void onCreate() {
        if (settingWebInteractor == null) {
            settingWebInteractor = new SettingWebInteractorImpl();
        }
        if (settingWebView != null) {
            settingWebView.setupToolbar();
            settingWebView.initContentView();
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
        settingWebView = null;
        settingWebInteractor = null;
    }
}
