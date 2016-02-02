package me.uteacher.www.herocat.module.homepage.homeTab;

import me.uteacher.www.herocat.app.IPresenterLife;
import me.uteacher.www.herocat.module.homepage.homeTab.recyclerViewAdapter.ICardPresenter;

/**
 * Created by HL0521 on 2016/1/20.
 */
public interface IHomeTabPresenter extends IPresenterLife, ICardPresenter {

    public void onLoadMore(int currentCount);

    public void onRefresh();

    public void onRecyclerViewScrollUp();

    public void onRecyclerViewScrollDown();

    public void onFloatActionButtonClicked();

}
