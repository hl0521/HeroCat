package me.uteacher.www.herocat.module.favourite;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.uteacher.www.herocat.R;
import me.uteacher.www.herocat.app.BaseFragment;
import me.uteacher.www.herocat.model.instagram.IInstagramModel;
import me.uteacher.www.herocat.module.homepage.homeTab.recyclerViewAdapter.ICardPresenter;
import me.uteacher.www.herocat.module.homepage.homeTab.recyclerViewAdapter.UltimateRecyclerViewAdapter;
import me.uteacher.www.herocat.module.main.IMainView;
import me.uteacher.www.herocat.module.main.MainActivity;

/**
 * Created by HL0521 on 2016/2/1.
 */
public class FavouriteFragment extends BaseFragment implements IFavouriteView {

    private static final String TAG = "FavouriteFragment";

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recyclerView)
    UltimateRecyclerView recyclerView;

    private LinearLayoutManager linearLayoutManager;
    private UltimateRecyclerViewAdapter ultimateRecyclerViewAdapter;

    private IMainView mainView;
    private IFavouritePresenter favouritePresenter;
    private Context context;

    public FavouriteFragment() {

    }

    public static FavouriteFragment newInstance(String param1, String param2) {
        FavouriteFragment fragment = new FavouriteFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
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
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (favouritePresenter == null) {
            favouritePresenter = new FavouritePresenterImpl(this);
        }

        favouritePresenter.onCreate();
    }

    @Override
    public void onResume() {
        super.onResume();

        favouritePresenter.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();

        favouritePresenter.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        favouritePresenter.onDestroy();
    }

    @Override
    public void initContentView() {

    }

    @Override
    public void showMessage(String msg) {
        mainView.showMessage(msg);
    }

    @Override
    public void setupToolbar() {
        toolbar.setTitle(R.string.favourite);
        // 设置 TooBar 的副标题
//        toolbar.setSubtitle("test");
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
        // Navigation Icon 要设定在 setSupoortActionBar 才有作用，否则会出现 back button
        toolbar.setNavigationIcon(R.drawable.icon_white_previous);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
    }

    @Override
    public IMainView getMainView() {
        return mainView;
    }

    @Override
    public void initAdapterDataSet(List<IInstagramModel> items) {
        ultimateRecyclerViewAdapter = new UltimateRecyclerViewAdapter(items);
        ultimateRecyclerViewAdapter.setCardPresenter(favouritePresenter);
    }

    @Override
    public void setupRecyclerView() {
        recyclerView.setHasFixedSize(false);

        linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(ultimateRecyclerViewAdapter);

        recyclerView.enableLoadmore();
        recyclerView.enableDefaultSwipeRefresh(true);

        recyclerView.setDefaultOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                favouritePresenter.onRefresh();
            }
        });

        recyclerView.setOnLoadMoreListener(new UltimateRecyclerView.OnLoadMoreListener() {
            @Override
            public void loadMore(int itemsCount, int maxLastVisiblePosition) {
                // TODO: 2016/2/1 下面代码可能有问题，需要关注！
                favouritePresenter.onLoadMore(itemsCount, 0);
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
            }
        });
    }

    @Override
    public void appendAdapterDateSet(List<IInstagramModel> items) {
        ultimateRecyclerViewAdapter.addItem(items);
    }

    @Override
    public void notifyAdapterDataSetChanged() {
        // 下面这个函数通知 Adapter 数据集发生改变，但是 数据集 的改变分为很多种类型，这个通知强迫
        // Adapter 把所有改变都检查一遍，效率较低。如果要提高效率，应该具体指出发生了哪种改变，详情见该
        // 函数的用法
        // TODO: 2016/2/1 这个函数会可能会影响 app 的性能，待优化！
        ultimateRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void scrollToPosition(int position) {

    }
}
