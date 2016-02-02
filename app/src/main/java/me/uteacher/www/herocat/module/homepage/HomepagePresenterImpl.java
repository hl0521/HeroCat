package me.uteacher.www.herocat.module.homepage;

import me.uteacher.www.herocat.app.BasePresenter;
import me.uteacher.www.herocat.module.main.IMainView;

/**
 * Created by HL0521 on 2016/1/20.
 */
public class HomepagePresenterImpl extends BasePresenter implements IHomepagePresenter {

    private IHomepageView homepageView;
    private IHomepageInteractor homepageInteractor;

    public HomepagePresenterImpl(IHomepageView homepageView) {
        this.homepageView = homepageView;
        if (homepageInteractor == null) {
            homepageInteractor = new HomepageInteractorImpl();
        }
    }

    @Override
    public void onCreate() {
        homepageView.initContentView();
        homepageView.setupToolBar();
        homepageView.setupViewPagerAdapter();
        // setupViewPager() 在 setupViewPagerAdapter() 内部执行了
//        homepageView.setupViewPager();
        homepageView.showFABtn();
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
    public void onFABClicked() {
    }
}
