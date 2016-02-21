package me.uteacher.www.herocat.module.homepage.homeTab.comment.commentAdapter;

import android.graphics.Bitmap;

/**
 * Created by HL0521 on 2016/2/20.
 */
public interface ICommentReplyView {

    public void setUserPortrait(Bitmap bitmap);

    public void setUserPortrait(int resId);

    public void setUserName(String userName);

    public void setPublishTime(String publishTime);

    public void setCommentLike();

    public void clearCommentLike();

    public void setCommentContent(CharSequence commentContent);

}
