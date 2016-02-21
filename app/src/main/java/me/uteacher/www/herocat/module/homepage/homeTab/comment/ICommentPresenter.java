package me.uteacher.www.herocat.module.homepage.homeTab.comment;

import me.uteacher.www.herocat.app.IPresenterLife;
import me.uteacher.www.herocat.module.homepage.homeTab.comment.commentAdapter.ICommentReplyPresenter;

/**
 * Created by HL0521 on 2016/1/27.
 */
public interface ICommentPresenter extends IPresenterLife, ICommentReplyPresenter {

    public void onLoadMore(int currentCount);

}
