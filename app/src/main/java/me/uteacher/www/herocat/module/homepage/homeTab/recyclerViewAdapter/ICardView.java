package me.uteacher.www.herocat.module.homepage.homeTab.recyclerViewAdapter;

import me.uteacher.www.herocat.model.instagram.IInstagramModel;

/**
 * Created by HL0521 on 2016/1/21.
 */
public interface ICardView {

    /**
     * 设置当前数据来源
     *
     * @param owner
     */
    public void setOwner(String owner);

    /**
     * 设置图片的源地址
     *
     * @param uri
     */
    public void setImageURI(String uri);

    /**
     * 设置 图片/视频 是否点赞(点赞人数)、收藏
     */
    public void setCardLike();

    public void clearCardLike();

    public void setCardLikeCount(int count);

    public void setCardFavourite();

    public void clearCardFavourite();

    /**
     * 显示图片的长宽比
     *
     * @param ratio
     */
    public void setImageRatio(float ratio);

    /**
     * 显示/隐藏 VideoView
     */
    public void showVideoView();

    public void hideVideoView();

    /**
     * 当为视频资源时，点击屏幕，显示/隐藏 播放/暂停 按钮
     */
    public void showVideoControlBtn();

    public void hideVideoControlBtn();

    /**
     * 显示/隐藏 视频进度条
     */
    public void showProgressBar();

    public void hideProgressBar();

    /**
     * 加载视频
     *
     * @param uri 视频源地址
     */
    public void loadVideo(String uri);

    /**
     * 播放 / 暂停 / 停止 视频
     */
    public void startVideo();

    public void pauseVideo();

    public void stopVideo();

    /**
     * 初始设置 VideoControlBtn 按键的功能
     *
     * @param cardPresenter  用来处理当前事件的 CardPresenter
     * @param instagramModel 被点击按钮所在的 InstagramModel
     */
    public void setupVideoControlBtn(ICardPresenter cardPresenter, IInstagramModel instagramModel);

    /**
     * 初始设置 VideoView
     *
     * @param cardPresenter  用来处理当前事件的 CardPresenter
     * @param instagramModel 被点击按钮所在的 InstagramModel
     */
    public void setupVideoView(ICardPresenter cardPresenter, IInstagramModel instagramModel);

    /**
     * 显示/隐藏 ImageView
     */
    public void showImageView();

    public void hideImageView();

    /**
     * 跳转到 评论 区
     */
    public void navigateToComment();

    /**
     * 设置以下四个按键的事件监听器：<code>ShareBtn</code>，<code>Comment</code>，
     * <code>Favourite</code>，<code>Like</code>
     *
     * @param cardPresenter  用来处理当前事件的 CardPresenter
     * @param instagramModel 被点击按钮所在的 InstagramModel
     */
    public void setupShareBtn(ICardPresenter cardPresenter, IInstagramModel instagramModel);

    public void setupCommentBtn(ICardPresenter cardPresenter, IInstagramModel instagramModel);

    public void setupFavouriteBtn(ICardPresenter cardPresenter, IInstagramModel instagramModel);

    public void setupLikeBtn(ICardPresenter cardPresenter, IInstagramModel instagramModel);

}
