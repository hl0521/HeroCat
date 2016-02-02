package me.uteacher.www.herocat.module.homepage.homeTab.recyclerViewAdapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.uteacher.www.herocat.R;
import me.uteacher.www.herocat.model.instagram.IInstagramModel;
import me.uteacher.www.herocat.module.main.MainActivity;

/**
 * Created by HL0521 on 2016/1/21.
 */
public class UltimateViewHolder extends UltimateRecyclerviewViewHolder implements ICardView {

    private Context context;

    @Bind(R.id.instagram_src)
    TextView instagramSrc;
    @Bind(R.id.video_view)
    VideoView videoView;
    @Bind(R.id.image_view)
    SimpleDraweeView imageView;
    @Bind(R.id.btn_video_control)
    ImageButton btnVideoControl;
    @Bind(R.id.like_cardview)
    TextView likeCardview;
    @Bind(R.id.comment_cardview)
    TextView commentCardview;
    @Bind(R.id.favourite_cardview)
    TextView favouriteCardview;
    @Bind(R.id.share_cardview)
    TextView shareCardview;
    @Bind(R.id.cardview)
    CardView cardview;
    @Bind(R.id.progressBar_video)
    ProgressBar progressBarVideo;

    public UltimateViewHolder(View itemView, boolean isItem) {
        super(itemView);

        if (isItem) {
            context = itemView.getContext();
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public void setOwner(String owner) {
        instagramSrc.setText(owner);
    }

    @Override
    public void setImageURI(String uri) {
        imageView.setImageURI(Uri.parse(uri));
    }

    @Override
    public void setCardLike() {
        Drawable drawable = context.getApplicationContext().getResources().getDrawable(R.drawable.like_filled);
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        }
        likeCardview.setCompoundDrawables(drawable, null, null, null);
    }

    @Override
    public void clearCardLike() {
        Drawable drawable = context.getApplicationContext().getResources().getDrawable(R.drawable.like_empty);
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        }
        likeCardview.setCompoundDrawables(drawable, null, null, null);
    }

    @Override
    public void setCardLikeCount(int count) {
        likeCardview.setText(String.valueOf(count));
    }

    @Override
    public void setCardFavourite() {
        Drawable drawable = context.getApplicationContext().getResources().getDrawable(R.drawable.star_filled);
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        }
        favouriteCardview.setCompoundDrawables(drawable, null, null, null);
    }

    @Override
    public void clearCardFavourite() {
        Drawable drawable = context.getApplicationContext().getResources().getDrawable(R.drawable.star_empty);
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        }
        favouriteCardview.setCompoundDrawables(drawable, null, null, null);
    }

    @Override
    public void setImageRatio(float ratio) {
        imageView.setAspectRatio(ratio);
    }

    @Override
    public void showVideoView() {
        videoView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideVideoView() {
        videoView.setVisibility(View.GONE);
    }

    @Override
    public void showVideoControlBtn() {
        btnVideoControl.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideVideoControlBtn() {
        btnVideoControl.setVisibility(View.GONE);
    }

    @Override
    public void showProgressBar() {
        progressBarVideo.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBarVideo.setVisibility(View.GONE);
    }

    @Override
    public void loadVideo(String uri) {

    }

    @Override
    public void startVideo() {
        videoView.start();
    }

    @Override
    public void stopVideo() {
        videoView.stopPlayback();
    }

    @Override
    public void showImageView() {
        imageView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideImageView() {
        imageView.setVisibility(View.GONE);
    }

    @Override
    public void navigateToComment() {
        // TODO: 2016/2/2 功能待添加！
    }

    @Override
    public void setupShareBtn(final ICardPresenter cardPresenter, final IInstagramModel instagramModel) {
        shareCardview.setClickable(true);
        shareCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardPresenter.onShareBtnClicked(UltimateViewHolder.this, instagramModel);
            }
        });
    }

    @Override
    public void setupCommentBtn(final ICardPresenter cardPresenter, final IInstagramModel instagramModel) {
        commentCardview.setClickable(true);
        commentCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardPresenter.onCommentBtnClicked(UltimateViewHolder.this, instagramModel);
            }
        });
    }

    @Override
    public void setupFavouriteBtn(final ICardPresenter cardPresenter, final IInstagramModel instagramModel) {
        favouriteCardview.setClickable(true);
        favouriteCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardPresenter.onFavouriteBtnClicked(UltimateViewHolder.this, instagramModel);
            }
        });
    }

    @Override
    public void setupLikeBtn(final ICardPresenter cardPresenter, final IInstagramModel instagramModel) {
        likeCardview.setClickable(true);
        likeCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardPresenter.onLikeBtnClicked(UltimateViewHolder.this, instagramModel);
            }
        });
    }

}
