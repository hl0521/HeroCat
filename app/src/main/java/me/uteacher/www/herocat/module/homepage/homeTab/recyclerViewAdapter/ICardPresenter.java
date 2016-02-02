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
     * 当 视频控制 按钮被点击时，相应的回调函数
     * 以下各函数 <code>onLikeBtnClicked</code>，<code>onFavouriteBtnClicked</code>，
     * <code>onShareBtnClicked</code>，<code>onVideoPrepared</code>，
     * <code>onCommentBtnClicked</code>等类似
     *
     * @param cardView       被点击的按钮所在的 CardView
     * @param instagramModel 被点击的按钮所在 CardView 的内容来源
     */
    public void onVideoControlBtnClicked(ICardView cardView, IInstagramModel instagramModel);

    public void onLikeBtnClicked(ICardView cardView, IInstagramModel instagramModel);

    public void onFavouriteBtnClicked(ICardView cardView, IInstagramModel instagramModel);

    public void onShareBtnClicked(ICardView cardView, IInstagramModel instagramModel);

    public void onCommentBtnClicked(ICardView cardView, IInstagramModel instagramModel);

    /**
     * 当视频准备好以后，具体功能描述待完善
     *
     * @param cardView
     * @param instagramModel
     */
    public void onVideoPrepared(ICardView cardView, IInstagramModel instagramModel);

    public void onVideoCompleted(ICardView cardView, IInstagramModel instagramModel);
}
