package me.uteacher.www.herocat.module.homepage.homeTab.comment;

import me.uteacher.www.herocat.app.BasePresenter;
import me.uteacher.www.herocat.module.homepage.homeTab.comment.commentAdapter.CommentReplyInteractorImpl;
import me.uteacher.www.herocat.module.homepage.homeTab.comment.commentAdapter.ICommentReplyInteractor;

/**
 * Created by HL0521 on 2016/1/27.
 */
public class CommentPresenterImpl extends BasePresenter implements ICommentPresenter {

    private ICommentView commentView;
    private ICommentInteractor commentInteractor;
    private ICommentReplyInteractor commentReplyInteractor;

    public CommentPresenterImpl(ICommentView commentView) {
        this.commentView = commentView;

        if (commentInteractor == null) {
            commentInteractor = new CommentInteractorImpl();
        }
        if (commentReplyInteractor == null) {
            commentReplyInteractor = new CommentReplyInteractorImpl();
        }
    }

    @Override
    public void onCreate() {
        if (commentView != null) {
            commentView.setupToobar();
            commentView.initContentView();
            commentView.initAdapterDataSet();
            commentView.setupRecyclerView();
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
        commentView = null;
        commentInteractor = null;
        commentReplyInteractor = null;
    }

    @Override
    public void onCommentReplyViewCreated() {

    }

    @Override
    public void onCommentReplyViewBind() {

    }

    @Override
    public void onCommentLikeClicked() {

    }

    @Override
    public void onLoadMore(int currentCount) {
        System.out.println("======onLoadMoreEnd=======");
        commentView.getCommentRecyclerViewAdapter().notifyItemChanged(10);
    }
}
