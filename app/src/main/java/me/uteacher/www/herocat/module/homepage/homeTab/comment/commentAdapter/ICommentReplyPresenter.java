package me.uteacher.www.herocat.module.homepage.homeTab.comment.commentAdapter;

import me.uteacher.www.herocat.app.IPresenterLife;

/**
 * Created by HL0521 on 2016/2/20.
 */
public interface ICommentReplyPresenter extends IPresenterLife {

    public void onCommentReplyViewCreated();

    public void onCommentReplyViewBind();

    public void onCommentLikeClicked();

}
