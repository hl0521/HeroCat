package me.uteacher.www.herocat.module.homepage.homeTab.comment.commentAdapter;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.TextView;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.uteacher.www.herocat.R;
import me.uteacher.www.herocat.widget.CircleImageView;

/**
 * Created by HL0521 on 2016/2/20.
 */
public class CommentViewHolder extends UltimateRecyclerviewViewHolder implements ICommentReplyView {

    @Bind(R.id.user_portrait)
    CircleImageView userPortrait;
    @Bind(R.id.user_name)
    TextView userName;
    @Bind(R.id.publish_time)
    TextView publishTime;
    @Bind(R.id.comment_like)
    TextView commentLike;
    @Bind(R.id.comment_content)
    TextView commentContent;

    public CommentViewHolder(View itemView, boolean isItem) {
        super(itemView);

        if (isItem) {
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public void setUserPortrait(Bitmap bitmap) {
        userPortrait.setImageResource(R.drawable.ic_launcher);
    }

    @Override
    public void setUserPortrait(int resId) {
        userPortrait.setImageResource(resId);
    }

    @Override
    public void setUserName(String userName) {
        this.userName.setText(userName);
    }

    @Override
    public void setPublishTime(String publishTime) {
        this.publishTime.setText(publishTime);
    }

    @Override
    public void setCommentLike() {
        commentLike.setText("(88)");
    }

    @Override
    public void clearCommentLike() {

    }

    @Override
    public void setCommentContent(CharSequence commentContent) {
        this.commentContent.setText(commentContent);
    }

}
