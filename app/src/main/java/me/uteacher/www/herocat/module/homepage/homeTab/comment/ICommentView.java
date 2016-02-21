package me.uteacher.www.herocat.module.homepage.homeTab.comment;

import me.uteacher.www.herocat.app.IBaseView;
import me.uteacher.www.herocat.app.IDebug;
import me.uteacher.www.herocat.module.homepage.homeTab.comment.commentAdapter.CommentRecyclerViewAdapter;
import me.uteacher.www.herocat.module.main.IMainView;

/**
 * Created by HL0521 on 2016/1/27.
 */
public interface ICommentView extends IBaseView, IDebug {

    public void setupToobar();

    public void setupRecyclerView();

    public IMainView getMainView();

    public CommentRecyclerViewAdapter getCommentRecyclerViewAdapter();

    public void initAdapterDataSet();

    public void appendAdapterDataSet();

    public void notifyAdapterDataSetChanged();

}
