package me.uteacher.www.herocat.module.setting;

import me.uteacher.www.herocat.app.BasePresenter;

/**
 * Created by HL0521 on 2016/2/1.
 */
public class SettingPresenterImpl extends BasePresenter implements ISettingPresenter {

    private ISettingView settingView;
    private ISettingInteractor settingInteractor;

    public SettingPresenterImpl(ISettingView settingView) {
        this.settingView = settingView;
        if (settingInteractor == null) {
            settingInteractor = new SettingInteractorImpl();
        }
    }

    @Override
    public void onCreate() {
        settingView.initContentView();
        settingView.setupToolbar();
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
}
