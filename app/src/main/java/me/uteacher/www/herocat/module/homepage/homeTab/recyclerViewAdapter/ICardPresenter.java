package me.uteacher.www.herocat.module.homepage.homeTab.recyclerViewAdapter;

import me.uteacher.www.herocat.model.instagram.IInstagramModel;

/**
 * Created by HL0521 on 2016/1/21.
 */
public interface ICardPresenter {

    /**
     * 当 ViewHolder被创建时调用，用来对 ViewHolder 的初始化设置
     *
     * @param cardView
     */
    public void onCardViewCreated(ICardView cardView);

    /**
     * 当 Adapter 中的 ViewHolder 被绑定时，需要对 ViewHolder 中的相应显示内容（CardView里面的各
     * 种控件）进行处理
     *
     * @param cardView       ViewHolder绑定时，其中待处理的 CardView 视图
     * @param instagramModel CardView视图中的内容来自于 instagramModel
     */
    public void onCardViewBind(ICardView cardView, IInstagramModel instagramModel);

    /**
     * * 当 视频播放 / 视频暂停 按钮被点击时，相应的回调函数
     *
     * @param cardView       被点击的按钮所在的 CardView
     * @param instagramModel 被点击的按钮所在 CardView 的内容来源
     * @param firstLoad      视频是否是第一次加载：true-->是；false-->否
     */
    public void onVideoPlayBtnClicked(ICardView cardView, IInstagramModel instagramModel, boolean firstLoad);

    public void onVideoPauseBtnClicked(ICardView cardView, IInstagramModel instagramModel);

    /**
     * 当视频准备好以后，所进行的操作
     *
     * @param cardView
     * @param instagramModel
     */
    public void onVideoPrepared(ICardView cardView, IInstagramModel instagramModel);

    /**
     * 当视频播放结束后，所进行的操作
     *
     * @param cardView
     * @param instagramModel
     */
    public void onVideoCompleted(ICardView cardView, IInstagramModel instagramModel);

    /**
     * 当 喜欢 / 收藏 / 分享 / 评论 按钮被点击时，相应的回调函数
     *
     * @param cardView       被点击的按钮所在的 CardView
     * @param instagramModel 被点击的按钮所在 CardView 的内容来源
     */
    public void onLikeBtnClicked(ICardView cardView, IInstagramModel instagramModel);

    public void onFavouriteBtnClicked(ICardView cardView, IInstagramModel instagramModel);

    public void onShareBtnClicked(ICardView cardView, IInstagramModel instagramModel);

    public void onCommentBtnClicked(ICardView cardView, IInstagramModel instagramModel);
}
