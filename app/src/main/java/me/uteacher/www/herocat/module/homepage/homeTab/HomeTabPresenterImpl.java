package me.uteacher.www.herocat.module.homepage.homeTab;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.uteacher.www.herocat.app.BasePresenter;
import me.uteacher.www.herocat.config.DefaultAppConfig;
import me.uteacher.www.herocat.model.application.IApplicationModel;
import me.uteacher.www.herocat.model.instagram.IInstagramModel;
import me.uteacher.www.herocat.model.instagram.InstagramBean;
import me.uteacher.www.herocat.model.user.IUserModel;
import me.uteacher.www.herocat.module.homepage.OnFABClickedListener;
import me.uteacher.www.herocat.module.homepage.homeTab.recyclerViewAdapter.CardInteractorImpl;
import me.uteacher.www.herocat.module.homepage.homeTab.recyclerViewAdapter.ICardInteractor;
import me.uteacher.www.herocat.module.homepage.homeTab.recyclerViewAdapter.ICardView;
import me.uteacher.www.herocat.module.main.IDialogCallback;
import me.uteacher.www.herocat.module.main.IShareToSNSCallback;

/**
 * Created by HL0521 on 2016/1/20.
 * 这个类实现了 ICardPresenter
 * HomeTab （是一个Fragment） 中包含 RecyclerView ，RecyclerView 中包含大量 CardView ，此处 CardView 的
 * 各种操作就在 HomeTab 这个Fragment 中进行，因此需要实现 ICardPresenter
 */
public class HomeTabPresenterImpl extends BasePresenter implements IHomeTabPresenter, OnFABClickedListener {

    private IHomeTabView homeTabView;
    private IHomeTabInteractor homeTabInteractor;

    private ICardInteractor cardInteractor;

    // 正在加载更多，是：true；否：false
    private boolean isOnLoad = false;
    // 正在上拉刷新，是：true；否：false
    private boolean isOnRefresh = false;

    public HomeTabPresenterImpl(IHomeTabView homeTabView) {
        this.homeTabView = homeTabView;

        if (homeTabInteractor == null) {
            this.homeTabInteractor = new HomeTabInteractorImpl();
        }
        if (cardInteractor == null) {
            this.cardInteractor = new CardInteractorImpl();
        }
    }

    @Override
    public void onCreate() {
        homeTabView.initContentView();

        String category = homeTabView.getCategory();
        if (category.equalsIgnoreCase("hot")) {
            homeTabInteractor.getInstagramItemsByPopularity(new IHomeTabInteractor.OnGetInstagramItemListener() {
                @Override
                public void onSuccess(List<IInstagramModel> list) {
//                    homeTabView.initAdapterDataSet(list);
//                    homeTabView.setupRecyclerView();
                    homeTabView.appendAdapterDataSet(list);
                }

                @Override
                public void onFailure(String error) {
                    // TODO: 2016/1/28 从缓存读取数据，或者从云端读取数据时，加入缓存机制！
                    homeTabView.getMainView().showMessage(error);
                }
            });
        } else {
            homeTabInteractor.getInstagramItemsByGroup(category, new IHomeTabInteractor.OnGetInstagramItemListener() {
                @Override
                public void onSuccess(List<IInstagramModel> list) {
//                    homeTabView.initAdapterDataSet(list);
//                    homeTabView.setupRecyclerView();
                    homeTabView.appendAdapterDataSet(list);
                }

                @Override
                public void onFailure(String error) {
                    homeTabView.getMainView().showMessage(error);
                }
            });
        }

        List<IInstagramModel> list = new ArrayList<>();
        homeTabView.initAdapterDataSet(list);
        homeTabView.setupRecyclerView();
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        homeTabView = null;
        homeTabInteractor = null;
        cardInteractor = null;
    }

    @Override
    public void onLoadMore(int currentCount) {
        if (isOnLoad) {
            return;
        }

        isOnLoad = true;

        String category = homeTabView.getCategory();
        if (category.equalsIgnoreCase("hot")) {
            homeTabInteractor.getInstagramItemsByPopularity(DefaultAppConfig.DEFAULT_ITEM_LIMIT
                    , currentCount, new IHomeTabInteractor.OnGetInstagramItemListener() {
                @Override
                public void onSuccess(List<IInstagramModel> list) {
                    homeTabView.appendAdapterDataSet(list);
                    isOnLoad = false;
                }

                @Override
                public void onFailure(String error) {
                    homeTabView.getMainView().showMessage(error);
                    isOnLoad = false;
                }
            });
        } else {
            homeTabInteractor.getInstagramItemsByGroup(DefaultAppConfig.DEFAULT_ITEM_LIMIT
                    , currentCount, category, new IHomeTabInteractor.OnGetInstagramItemListener() {
                @Override
                public void onSuccess(List<IInstagramModel> list) {
                    homeTabView.appendAdapterDataSet(list);
                    isOnLoad = false;
                }

                @Override
                public void onFailure(String error) {
                    homeTabView.getMainView().showMessage(error);
                    isOnLoad = false;
                }
            });
        }
    }

    @Override
    public void onRefresh() {
        if (isOnRefresh) {
            return;
        }

        isOnRefresh = true;

        String category = homeTabView.getCategory();
        if (category.equalsIgnoreCase("hot")) {
            homeTabInteractor.getInstagramItemsByPopularity(new IHomeTabInteractor.OnGetInstagramItemListener() {
                @Override
                public void onSuccess(List<IInstagramModel> list) {
                    homeTabView.clearAdapterDataSet();
                    homeTabView.appendAdapterDataSet(list);
                    isOnRefresh = false;
                }

                @Override
                public void onFailure(String error) {
                    homeTabView.getMainView().showMessage(error);
                    isOnRefresh = false;
                }
            });
        } else {
            homeTabInteractor.getInstagramItemsByGroup(category, new IHomeTabInteractor.OnGetInstagramItemListener() {
                @Override
                public void onSuccess(List<IInstagramModel> list) {
                    homeTabView.clearAdapterDataSet();
                    homeTabView.appendAdapterDataSet(list);
                    isOnRefresh = false;
                }

                @Override
                public void onFailure(String error) {
                    homeTabView.getMainView().showMessage(error);
                    isOnRefresh = false;
                }
            });
        }
    }

    @Override
    public void onRecyclerViewScrollUp() {
        homeTabView.getHomePageView().hideFABtn();
    }

    @Override
    public void onRecyclerViewScrollDown() {
        homeTabView.getHomePageView().showFABtn();
    }

    @Override
    public void onFloatActionButtonClicked() {
        onFABClicked();
    }

    @Override
    public void onFABClicked() {
        if (homeTabView.getUserVisibleHint()) {
            homeTabView.scrollToPosition(0);
        }
    }

    @Override
    public void onCardViewCreated(ICardView cardView) {
        cardView.setImageRatio(1.0F);
    }

    @Override
    public void onCardViewBind(ICardView cardView, IInstagramModel instagramModel) {
        InstagramBean instagramBean = instagramModel.getInstagramBean();

        String owner = instagramBean.getOwner();
        cardView.setOwner((owner.length() < 12) ? owner : owner.substring(0, 11));
        cardView.setImageURI(instagramBean.getDisplay_src());
        cardView.setCardLikeCount(instagramBean.getLike_count());

        IUserModel userModel = homeTabView.getMainView().getUserModel();
        if (userModel != null) {
            if (instagramModel.isUserLiked(userModel)) {
                cardView.setCardLike();
            } else {
                cardView.clearCardLike();
            }

            if (instagramModel.isUserStored(userModel)) {
                cardView.setCardFavourite();
            } else {
                cardView.clearCardFavourite();
            }
        }

        cardView.showImageView();
        cardView.hideVideoControlBtn();
        cardView.hideProgressBar();
        cardView.setupCommentBtn(this, instagramModel);
        cardView.setupFavouriteBtn(this, instagramModel);
        cardView.setupLikeBtn(this, instagramModel);
        cardView.setupShareBtn(this, instagramModel);

        if (instagramBean.getIs_video()) {
            cardView.showVideoView();
            cardView.showVideoControlBtn();
            cardView.setupVideoControlBtn(this, instagramModel);
            cardView.setupVideoView(this, instagramModel);
        } else {
            cardView.hideVideoView();
            cardView.hideVideoControlBtn();
        }
    }

    @Override
    public void onVideoPlayBtnClicked(final ICardView cardView, IInstagramModel instagramModel, boolean firstLoad) {
        if (!firstLoad) {
            cardView.hideImageView();
            cardView.startVideo();
            return;
        }

        if (instagramModel.getInstagramBean().getIs_video()) {
            homeTabInteractor.locateVideoUri(instagramModel, new IHomeTabInteractor.onVideoUriLocatedCallback() {
                @Override
                public void onSuccess(IInstagramModel instagramModel, String uri) {
                    cardView.loadVideo(uri);
                    cardView.hideImageView();
                    cardView.startVideo();
                }

                @Override
                public void onFailure(int error) {
                    homeTabView.getMainView().showMessage("Play error : " + error);
                }
            });
        }
    }

    @Override
    public void onVideoPauseBtnClicked(ICardView cardView, IInstagramModel instagramModel) {
        if (instagramModel.getInstagramBean().getIs_video()) {
            cardView.pauseVideo();
            cardView.showVideoControlBtn();
        }
    }

    @Override
    public void onVideoPrepared(ICardView cardView, IInstagramModel instagramModel) {

    }

    @Override
    public void onVideoCompleted(ICardView cardView, IInstagramModel instagramModel) {
        if (instagramModel.getInstagramBean().getIs_video()) {
            cardView.showVideoControlBtn();
            cardView.showImageView();
        }
    }

    @Override
    public void onLikeBtnClicked(final ICardView cardView, IInstagramModel instagramModel) {
        IUserModel userModel = homeTabView.getMainView().getUserModel();
        if (userModel != null) {  // 用户已登陆
            if (instagramModel.isUserLiked(userModel)) {  // 用户取消了 喜欢
                cardInteractor.userCancelLikeItem(instagramModel, userModel, new ICardInteractor.OnActionDoneCallback() {
                    @Override
                    public void onSuccess(IUserModel userModel, IInstagramModel item) {
                        item.userCancelLike(userModel);
                        // TODO: 2016/1/31 下面这个通知数据改变，有待优化，以进一步提高性能！
                        homeTabView.notifyAdapterDataSetChanged();
                    }

                    @Override
                    public void onFailure(String error) {
                        homeTabView.getMainView().showMessage(error);
                    }
                });
            } else {  // 用户点击了 喜欢
                cardInteractor.userLikeItem(instagramModel, userModel, new ICardInteractor.OnActionDoneCallback() {
                    @Override
                    public void onSuccess(IUserModel userModel, IInstagramModel item) {
                        item.userLikes(userModel);
                        // TODO: 2016/1/31 下面这个通知数据改变，有待优化，以进一步提高性能！
                        homeTabView.notifyAdapterDataSetChanged();
                    }

                    @Override
                    public void onFailure(String error) {
                        homeTabView.getMainView().showMessage(error);
                    }
                });
            }
        } else { // 用户未登陆
            homeTabView.getMainView().showNeedLoginDialog("请先登陆", "该操作需要用户登陆", "登陆"
                    , "取消", new IDialogCallback() {
                @Override
                public void onPositiveBtnClicked() {
                    homeTabView.getMainView().navigateToSignin();
                }

                @Override
                public void onNegativeBtnClicked() {

                }
            });
        }
    }

    @Override
    public void onFavouriteBtnClicked(final ICardView cardView, IInstagramModel instagramModel) {
        IUserModel userModel = homeTabView.getMainView().getUserModel();
        if (userModel != null) {
            if (instagramModel.isUserStored(userModel)) {  // 用户点击了 取消收藏
                cardInteractor.userCancelFavouriteItem(instagramModel, userModel, new ICardInteractor.OnActionDoneCallback() {
                    @Override
                    public void onSuccess(IUserModel userModel, IInstagramModel item) {
                        item.userCancelFavourite(userModel);
                        // TODO: 2016/2/1 下面这个通知数据改变，有待优化，以进一步提高性能！
                        homeTabView.notifyAdapterDataSetChanged();
                    }

                    @Override
                    public void onFailure(String error) {
                        homeTabView.getMainView().showMessage(error);
                    }
                });
            } else {  // 用户点击了收藏
                cardInteractor.userFavouriteItem(instagramModel, userModel, new ICardInteractor.OnActionDoneCallback() {
                    @Override
                    public void onSuccess(IUserModel userModel, IInstagramModel item) {
                        item.userFavourite(userModel);
                        // TODO: 2016/2/1 下面这个通知数据改变，有待优化，以进一步提高性能！
                        homeTabView.notifyAdapterDataSetChanged();
                    }

                    @Override
                    public void onFailure(String error) {
                        homeTabView.getMainView().showMessage(error);
                    }
                });
            }
        } else {  // 用户未登陆
            homeTabView.getMainView().showNeedLoginDialog("请先登陆", "该操作需要用户登陆", "登陆"
                    , "取消", new IDialogCallback() {
                @Override
                public void onPositiveBtnClicked() {
                    homeTabView.getMainView().navigateToSignin();
                }

                @Override
                public void onNegativeBtnClicked() {

                }
            });
        }
    }

    private void shareToSNS(final String snsTpye, IApplicationModel applicationModel, final IInstagramModel instagramModel) {
        HashMap<String, String> infoMap;
        if (instagramModel.getInstagramBean().getIs_video()) {
            infoMap = applicationModel.getShareInfo("video");
        } else {
            infoMap = applicationModel.getShareInfo("image");
        }

        final String title = infoMap.get("title");
        final String description = infoMap.get("desc");
        final String url = infoMap.get("url");
        final String imageUrl = instagramModel.getInstagramBean().getDisplay_src();

        homeTabInteractor.getImageStream(imageUrl, new IHomeTabInteractor.OnGetImageStreamCallback() {
            @Override
            public void onSuccess(InputStream inputStream) {
                switch (snsTpye) {
                    case "weixin":
                        homeTabView.getMainView().shareToWechat(url + instagramModel.getInstagramBean().getObjectId()
                                , title, description, inputStream, false);
                        break;
                    case "weixin timeline":
                        homeTabView.getMainView().shareToWechat(url + instagramModel.getInstagramBean().getObjectId()
                                , title, description, inputStream, true);
                        break;
                    case "qq":
                        homeTabView.getMainView().shareToQQ(url + instagramModel.getInstagramBean().getObjectId()
                                , title, description, imageUrl, "英雄猫");
                        break;
                    case "weibo":
                        homeTabView.getMainView().shareToWeibo(url + instagramModel.getInstagramBean().getObjectId()
                                , title, description, inputStream);
                        break;
                    default:
                }
            }

            @Override
            public void onFailure(String error) {
                homeTabView.getMainView().showMessage(error);
            }
        });
    }

    @Override
    public void onShareBtnClicked(ICardView cardView, IInstagramModel instagramModel) {
        final IApplicationModel applicationModel = homeTabView.getMainView().getApplicationModel();

        homeTabView.getMainView().showShareToSnsDialog("分享到", "取消", instagramModel, new IShareToSNSCallback() {
            @Override
            public void onShareToWechatClicked(IInstagramModel instagramModel) {
                shareToSNS("weixin", applicationModel, instagramModel);
            }

            @Override
            public void onShareToWechatTimelineClicked(IInstagramModel instagramModel) {
                shareToSNS("weixin timeline", applicationModel, instagramModel);
            }

            @Override
            public void onShareToQQClicked(IInstagramModel instagramModel) {
                shareToSNS("qq", applicationModel, instagramModel);
            }

            @Override
            public void onShareToWeiboClicked(IInstagramModel instagramModel) {
                shareToSNS("weibo", applicationModel, instagramModel);
            }
        });
    }

    @Override
    public void onCommentBtnClicked(ICardView cardView, IInstagramModel instagramModel) {
        homeTabView.navigateToComment(instagramModel);
    }
}
