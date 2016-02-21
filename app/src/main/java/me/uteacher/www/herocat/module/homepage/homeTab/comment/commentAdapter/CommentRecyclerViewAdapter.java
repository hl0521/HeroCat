package me.uteacher.www.herocat.module.homepage.homeTab.comment.commentAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;

import me.uteacher.www.herocat.R;

/**
 * Created by HL0521 on 2016/2/20.
 */
public class CommentRecyclerViewAdapter extends UltimateViewAdapter<CommentViewHolder> {

    // TODO: 2016/2/20 模型待完善！
    //    private List<ICommentModel> itemList;
    private ICommentReplyPresenter commentReplyPresenter = null;

    public CommentRecyclerViewAdapter() {

    }

    public void setCommentReplyPresenter(ICommentReplyPresenter commentReplyPresenter) {
        this.commentReplyPresenter = commentReplyPresenter;
    }

    public ICommentReplyPresenter getCommentReplyPresenter() {
        return commentReplyPresenter;
    }

    @Override
    public CommentViewHolder getViewHolder(View view) {
        return new CommentViewHolder(view, false);
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
        CommentViewHolder commentViewHolder = new CommentViewHolder(view, true);
        if (commentReplyPresenter != null) {
            commentReplyPresenter.onCommentReplyViewCreated();
        }
        return commentViewHolder;
    }

    @Override
    public int getAdapterItemCount() {
        return 10;
    }

    @Override
    public long generateHeaderId(int position) {
        return -1;
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        if (commentReplyPresenter != null) {
            commentReplyPresenter.onCommentReplyViewBind();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }
}
