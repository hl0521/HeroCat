package me.uteacher.www.herocat.module.homepage.homeTab;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.squareup.otto.Subscribe;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.uteacher.www.herocat.R;
import me.uteacher.www.herocat.app.BaseFragment;
import me.uteacher.www.herocat.model.instagram.IInstagramModel;
import me.uteacher.www.herocat.module.homepage.FABClickedEvent;
import me.uteacher.www.herocat.module.homepage.IHomepageView;
import me.uteacher.www.herocat.module.homepage.OnFABClickedListener;
import me.uteacher.www.herocat.module.homepage.homeTab.comment.CommentFragment;
import me.uteacher.www.herocat.module.homepage.homeTab.recyclerViewAdapter.ICardPresenter;
import me.uteacher.www.herocat.module.homepage.homeTab.recyclerViewAdapter.UltimateRecyclerViewAdapter;
import me.uteacher.www.herocat.module.main.IMainView;
import me.uteacher.www.herocat.util.BusProvider;

/**
 * Created by HL0521 on 2016/1/20.
 */
public class HomeTabFragment extends BaseFragment implements IHomeTabView {

    private static final String TAG = "HomeTabFragment";

    public static final String CATEGORY = "category";
    public static final String ARG_PARAM2 = "param2";

    @Bind(R.id.recyclerView)
    UltimateRecyclerView recyclerView;

    private String category;

    private Context context;
    private IMainView mainView;
    private IHomepageView homepageView;
    private IHomeTabPresenter homeTabPresenter;

    private UltimateRecyclerViewAdapter ultimateRecyclerViewAdapter;
    private ICardPresenter cardPresenter;

    private int onScrollThreshold = 10;

    public HomeTabFragment() {

    }

    public static HomeTabFragment newInstance(String category, String param2) {
        HomeTabFragment fragment = new HomeTabFragment();
        Bundle args = new Bundle();
        args.putString(CATEGORY, category);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;

        try {
            mainView = (IMainView) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement IMainView");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            category = getArguments().getString(CATEGORY);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hometab, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        homeTabPresenter = new HomeTabPresenterImpl(this);
        homeTabPresenter.onCreate();
    }

    @Override
    public void onResume() {
        super.onResume();

        BusProvider.getInstance().register(this);
        homeTabPresenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();

        BusProvider.getInstance().unregister(this);
    }

    @Override
    public void onStop() {
        super.onStop();

        homeTabPresenter.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);

        homeTabPresenter.onDestroy();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        homeTabPresenter.onDestroy();
    }

    @Subscribe
    public void onFABClicked(FABClickedEvent event) {
        homeTabPresenter.onFloatActionButtonClicked();
    }

    @Override
    public void initContentView() {
        if (getParentFragment() != null) {
            homepageView = (IHomepageView) getParentFragment();
            mainView = homepageView.getMainView();
        }
    }

    @Override
    public void showMessage(String msg) {
        homepageView.getMainView().showMessage(msg);
    }

    @Override
    public IHomepageView getHomePageView() {
        return homepageView;
    }

    @Override
    public IMainView getMainView() {
        return mainView;
    }

    @Override
    public void initAdapterDataSet(List<IInstagramModel> list) {
        ultimateRecyclerViewAdapter = new UltimateRecyclerViewAdapter(list);
        ultimateRecyclerViewAdapter.setCardPresenter(homeTabPresenter);
    }

    @Override
    public void appendAdapterDataSet(List<IInstagramModel> list) {
        ultimateRecyclerViewAdapter.addItem(list);
        notifyAdapterDataSetChanged();
    }

    @Override
    public void clearAdapterDataSet() {
        ultimateRecyclerViewAdapter.clearItem();
    }

    @Override
    public void notifyAdapterDataSetChanged() {
        // 下面这个函数通知 Adapter 数据集发生改变，但是 数据集 的改变分为很多种类型，这个通知强迫
        // Adapter 把所有改变都检查一遍，效率较低。如果要提高效率，应该具体指出发生了哪种改变，详情见该
        // 函数的用法
        // TODO: 2016/1/24 这个函数会可能会影响 app 的性能，待优化！
        ultimateRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void setupRecyclerView() {
        recyclerView.setHasFixedSize(false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(ultimateRecyclerViewAdapter);

        recyclerView.enableLoadmore();
        recyclerView.enableDefaultSwipeRefresh(true);

        recyclerView.setDefaultOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                homeTabPresenter.onRefresh();
            }
        });

        recyclerView.setOnLoadMoreListener(new UltimateRecyclerView.OnLoadMoreListener() {
            @Override
            public void loadMore(int itemsCount, int maxLastVisiblePosition) {
                // itemCount 是 Adapter 中所有数据的个数（需要考虑 UltimateRecyclerView 中加入头尾等情况）
                // maxLastVisiblePosition 是最后一个 可以看到的数据 的位置编号
                // 具体使用的时候，需要调试看看数据的输出
                homeTabPresenter.onLoadMore(maxLastVisiblePosition);
                Log.d(TAG, "itemCount = " + itemsCount + "; maxLastVisiblePosition = " + maxLastVisiblePosition);
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                boolean isSignificantDelta = Math.abs(dy) > onScrollThreshold;
                if (isSignificantDelta) {
                    if (dy > 0) {
                        homeTabPresenter.onRecyclerViewScrollUp();
                    } else {
                        homeTabPresenter.onRecyclerViewScrollDown();
                    }
                }
            }
        });

    }

    @Override
    public void scrollToPosition(int position) {
        recyclerView.scrollVerticallyToPosition(position);
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public boolean getUserVisibleHint() {
        return super.getUserVisibleHint();
    }

    @Override
    public void navigateToComment(IInstagramModel instagramModel) {
        getParentFragment().getFragmentManager().beginTransaction().addToBackStack(null)
                .replace(R.id.main_container, CommentFragment.newInstance(instagramModel, null))
                .commit();
    }
}
