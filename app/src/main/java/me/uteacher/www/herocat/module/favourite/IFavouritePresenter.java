package me.uteacher.www.herocat.module.favourite;

import me.uteacher.www.herocat.app.IPresenterLife;
import me.uteacher.www.herocat.module.homepage.homeTab.recyclerViewAdapter.ICardPresenter;

/**
 * Created by HL0521 on 2016/2/1.
 */
public interface IFavouritePresenter extends IPresenterLife, ICardPresenter {

    public void onLoadMore(int currentCount, final int lastVisible);

    public void onRefresh();

}
