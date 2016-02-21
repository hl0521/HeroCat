package me.uteacher.www.herocat.module.homepage.homeTab.comment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.uteacher.www.herocat.R;
import me.uteacher.www.herocat.app.BaseFragment;
import me.uteacher.www.herocat.model.instagram.IInstagramModel;
import me.uteacher.www.herocat.model.instagram.InstagramModel;
import me.uteacher.www.herocat.module.homepage.homeTab.comment.commentAdapter.CommentRecyclerViewAdapter;
import me.uteacher.www.herocat.module.main.IMainView;
import me.uteacher.www.herocat.module.main.MainActivity;

/**
 * Created by HL0521 on 2016/1/27.
 */
public class CommentFragment extends BaseFragment implements ICommentView {

    private static final String TAG = "CommentFragment";

    private static final String ARG_INSTAGRAM_MODEL = "param1";
    private static final String ARG_PARAM2 = "param2";

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recyclerView)
    UltimateRecyclerView recyclerView;
    @Bind(R.id.comment_content)
    EditText commentContent;
    @Bind(R.id.comment_publish)
    Button commentPublish;
    @Bind(R.id.comment_layout)
    LinearLayout commentLayout;

    private IMainView mainView;
    private Context context;

    private ICommentPresenter commentPresenter;
    private IInstagramModel instagramModel;

    private CommentRecyclerViewAdapter commentRecyclerViewAdapter;

    private View recyclerViewHeader;
    private SimpleDraweeView imageView;

    public CommentFragment() {

    }

    public static CommentFragment newInstance(Parcelable parcel, String param2) {
        CommentFragment fragment = new CommentFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_INSTAGRAM_MODEL, parcel);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.context = context;
        mainView = (IMainView) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        instagramModel = getArguments().getParcelable(ARG_INSTAGRAM_MODEL);

        if (commentPresenter == null) {
            commentPresenter = new CommentPresenterImpl(this);
        }
        commentPresenter.onCreate();
    }


    @Override
    public void onResume() {
        super.onResume();

        commentPresenter.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();

        commentPresenter.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        commentPresenter.onDestroy();
        commentPresenter = null;
    }

    @Override
    public void initContentView() {

    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void setupToobar() {
        toolbar.setTitle(R.string.comment_detail);
        toolbar.setTitleTextColor(Color.WHITE);

        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.icon_white_previous);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
    }

    @Override
    public void initAdapterDataSet() {
        commentRecyclerViewAdapter = new CommentRecyclerViewAdapter();
        commentRecyclerViewAdapter.setCommentReplyPresenter(commentPresenter);
    }

    @Override
    public void setupRecyclerView() {
        recyclerView.setHasFixedSize(false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(commentRecyclerViewAdapter);

        recyclerViewHeader = LayoutInflater.from(context).inflate(R.layout.item_recycler_header, null);
        imageView = (SimpleDraweeView) recyclerViewHeader.findViewById(R.id.image_view);
        imageView.setImageURI(Uri.parse(instagramModel.getInstagramBean().getDisplay_src()));
        recyclerView.setParallaxHeader(recyclerViewHeader);

        recyclerView.enableLoadmore();
        recyclerView.setOnLoadMoreListener(new UltimateRecyclerView.OnLoadMoreListener() {
            @Override
            public void loadMore(int itemsCount, int maxLastVisiblePosition) {
                commentPresenter.onLoadMore(maxLastVisiblePosition);
            }
        });
    }

    @Override
    public CommentRecyclerViewAdapter getCommentRecyclerViewAdapter() {
        return commentRecyclerViewAdapter;
    }

    @Override
    public IMainView getMainView() {
        return mainView;
    }

    @Override
    public void appendAdapterDataSet() {

    }

    @Override
    public void notifyAdapterDataSetChanged() {
        // 下面这个函数通知 Adapter 数据集发生改变，但是 数据集 的改变分为很多种类型，这个通知强迫
        // Adapter 把所有改变都检查一遍，效率较低。如果要提高效率，应该具体指出发生了哪种改变，详情见该
        // 函数的用法
        // TODO: 2016/2/20 这个函数会可能会影响 app 的性能，待优化！
        commentRecyclerViewAdapter.notifyDataSetChanged();
    }

}
