package me.uteacher.www.herocat.module.favourite;

import java.io.PipedReader;
import java.util.ArrayList;
import java.util.List;

import me.uteacher.www.herocat.app.BasePresenter;
import me.uteacher.www.herocat.config.DefaultAppConfig;
import me.uteacher.www.herocat.model.instagram.IInstagramModel;
import me.uteacher.www.herocat.model.instagram.InstagramBean;
import me.uteacher.www.herocat.model.user.IUserModel;
import me.uteacher.www.herocat.module.homepage.homeTab.recyclerViewAdapter.CardInteractorImpl;
import me.uteacher.www.herocat.module.homepage.homeTab.recyclerViewAdapter.ICardInteractor;
import me.uteacher.www.herocat.module.homepage.homeTab.recyclerViewAdapter.ICardView;
import me.uteacher.www.herocat.module.main.IDialogCallback;

/**
 * Created by HL0521 on 2016/2/1.
 */
public class FavouritePresenterImpl extends BasePresenter implements IFavouritePresenter {

    private IFavouriteView favouriteView;
    private IFavouriteInteractor favouriteInteractor;

    private ICardInteractor cardInteractor;

    // 正在加载更多，是：true；否：false
    private boolean isOnLoad = false;
    // 正在上拉刷新，是：true；否：false
    private boolean isOnRefresh = false;

    public FavouritePresenterImpl(IFavouriteView favouriteView) {
        this.favouriteView = favouriteView;

        if (favouriteInteractor == null) {
            favouriteInteractor = new FavouriteInteractorImpl();
        }
        if (cardInteractor == null) {
            cardInteractor = new CardInteractorImpl();
        }
    }

    @Override
    public void onCreate() {
        favouriteView.initContentView();
        favouriteView.setupToolbar();

        IUserModel userModel = favouriteView.getMainView().getUserModel();
        if (userModel != null) {
            favouriteInteractor.getUserFavouriteItems(userModel
                    , new IFavouriteInteractor.OnGetUserFavouriteItemsListener() {
                @Override
                public void onSuccess(IUserModel userModel, List<IInstagramModel> instagramModels) {
                    favouriteView.appendAdapterDataSet(instagramModels);
                }

                @Override
                public void onFailure(String error) {
                    favouriteView.getMainView().showMessage(error);
                }
            });
        }

        List<IInstagramModel> list = new ArrayList<>();
        favouriteView.initAdapterDataSet(list);
        favouriteView.setupRecyclerView();
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {
        favouriteView = null;
        favouriteInteractor = null;
    }

    @Override
    public void onLoadMore(int currentCount, final int lastVisible) {
        if (isOnLoad) {
            return;
        }

        isOnLoad = true;
        IUserModel userModel = favouriteView.getMainView().getUserModel();

        if (userModel == null) {
            return;
        }

        favouriteInteractor.getUserFavouriteItems(userModel, DefaultAppConfig.DEFAULT_ITEM_LIMIT, lastVisible
                , new IFavouriteInteractor.OnGetUserFavouriteItemsListener() {
            @Override
            public void onSuccess(IUserModel userModel, List<IInstagramModel> instagramModels) {
                if (instagramModels.size() == 0) {
                }
                favouriteView.appendAdapterDataSet(instagramModels);
                isOnLoad = false;
            }

            @Override
            public void onFailure(String error) {
                favouriteView.getMainView().showMessage(error);
                isOnLoad = false;
            }
        });
    }

    @Override
    public void onRefresh() {
        if (isOnRefresh) {
            return;
        }

        isOnRefresh = true;

        IUserModel userModel = favouriteView.getMainView().getUserModel();

        if (userModel == null) {
            return;
        }

        favouriteInteractor.getUserFavouriteItems(userModel, new IFavouriteInteractor.OnGetUserFavouriteItemsListener() {
            @Override
            public void onSuccess(IUserModel userModel, List<IInstagramModel> instagramModels) {
                favouriteView.clearAdapterDataSet();
                favouriteView.appendAdapterDataSet(instagramModels);
                isOnRefresh = false;
            }

            @Override
            public void onFailure(String error) {
                favouriteView.getMainView().showMessage(error);
                isOnRefresh = false;
            }
        });
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

        IUserModel userModel = favouriteView.getMainView().getUserModel();
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
        } else {
            cardView.hideVideoView();
            cardView.hideVideoControlBtn();
        }
    }

    @Override
    public void onVideoPlayBtnClicked(ICardView cardView, IInstagramModel instagramModel, boolean firstLoad) {

    }

    @Override
    public void onVideoPauseBtnClicked(ICardView cardView, IInstagramModel instagramModel) {

    }

    @Override
    public void onVideoPrepared(ICardView cardView, IInstagramModel instagramModel) {

    }

    @Override
    public void onVideoCompleted(ICardView cardView, IInstagramModel instagramModel) {

    }

    @Override
    public void onLikeBtnClicked(ICardView cardView, IInstagramModel instagramModel) {
        IUserModel userModel = favouriteView.getMainView().getUserModel();
        if (userModel != null) {  // 用户已登陆
            if (instagramModel.isUserLiked(userModel)) {  // 用户取消了 喜欢
                cardInteractor.userCancelLikeItem(instagramModel, userModel, new ICardInteractor.OnActionDoneCallback() {
                    @Override
                    public void onSuccess(IUserModel userModel, IInstagramModel item) {
                        item.userCancelLike(userModel);
                        // TODO: 2016/1/31 下面这个通知数据改变，有待优化，以进一步提高性能！
                        favouriteView.notifyAdapterDataSetChanged();
                    }

                    @Override
                    public void onFailure(String error) {
                        favouriteView.getMainView().showMessage(error);
                    }
                });
            } else {  // 用户点击了 喜欢
                cardInteractor.userLikeItem(instagramModel, userModel, new ICardInteractor.OnActionDoneCallback() {
                    @Override
                    public void onSuccess(IUserModel userModel, IInstagramModel item) {
                        item.userLikes(userModel);
                        // TODO: 2016/1/31 下面这个通知数据改变，有待优化，以进一步提高性能！
                        favouriteView.notifyAdapterDataSetChanged();
                    }

                    @Override
                    public void onFailure(String error) {
                        favouriteView.getMainView().showMessage(error);
                    }
                });
            }
        }
    }

    @Override
    public void onFavouriteBtnClicked(ICardView cardView, IInstagramModel instagramModel) {
        IUserModel userModel = favouriteView.getMainView().getUserModel();
        if (userModel != null) {
            if (instagramModel.isUserStored(userModel)) {  // 用户点击了 取消收藏
                cardInteractor.userCancelFavouriteItem(instagramModel, userModel, new ICardInteractor.OnActionDoneCallback() {
                    @Override
                    public void onSuccess(IUserModel userModel, IInstagramModel item) {
                        item.userCancelFavourite(userModel);
                        // TODO: 2016/2/1 下面这个通知数据改变，有待优化，以进一步提高性能！
                        favouriteView.notifyAdapterDataSetChanged();
                    }

                    @Override
                    public void onFailure(String error) {
                        favouriteView.getMainView().showMessage(error);
                    }
                });
            } else {  // 用户点击了收藏
                cardInteractor.userFavouriteItem(instagramModel, userModel, new ICardInteractor.OnActionDoneCallback() {
                    @Override
                    public void onSuccess(IUserModel userModel, IInstagramModel item) {
                        item.userFavourite(userModel);
                        // TODO: 2016/2/1 下面这个通知数据改变，有待优化，以进一步提高性能！
                        favouriteView.notifyAdapterDataSetChanged();
                    }

                    @Override
                    public void onFailure(String error) {
                        favouriteView.getMainView().showMessage(error);
                    }
                });
            }
        }
    }

    @Override
    public void onShareBtnClicked(ICardView cardView, IInstagramModel instagramModel) {

    }

    @Override
    public void onCommentBtnClicked(ICardView cardView, IInstagramModel instagramModel) {

    }

}
