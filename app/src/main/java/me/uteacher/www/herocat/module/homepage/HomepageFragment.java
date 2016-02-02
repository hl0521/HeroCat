package me.uteacher.www.herocat.module.homepage;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.uteacher.www.herocat.R;
import me.uteacher.www.herocat.app.BaseFragment;
import me.uteacher.www.herocat.model.application.IApplicationModel;
import me.uteacher.www.herocat.module.homepage.homeTab.HomeTabFragment;
import me.uteacher.www.herocat.module.homepage.viewPagerAdapter.ViewPagerAdapter;
import me.uteacher.www.herocat.module.main.IMainView;
import me.uteacher.www.herocat.module.main.MainActivity;
import me.uteacher.www.herocat.util.BusProvider;

/**
 * Created by HL0521 on 2016/1/20.
 */
public class HomepageFragment extends BaseFragment implements IHomepageView {

    private static final String TAG = "HomepageFragment";

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tablayout)
    TabLayout tablayout;
    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.floatActionButton)
    FloatingActionButton floatActionButton;
    @Bind(R.id.fragment_home)
    CoordinatorLayout fragmentHome;

    private IHomepagePresenter homepagePresenter;

    private Context context;
    private IMainView mainView;

    private ViewPagerAdapter viewPagerAdapter;

    private OnFABClickedListener onFABClickedListener;

    public HomepageFragment() {
        // Required empty public constructor
    }

    public static HomepageFragment newInstance(String param1, String param2) {
        HomepageFragment homepageFragment = new HomepageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        homepageFragment.setArguments(args);
        return homepageFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mainView = (IMainView) getActivity();
            this.context = context;
        } catch (ClassCastException e) {
            throw new ClassCastException(TAG + getActivity().toString() + " must implement IMainView");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homepage, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (homepagePresenter == null) {
            homepagePresenter = new HomepagePresenterImpl(this);
        }
        homepagePresenter.onCreate();
    }

    @Override
    public void onResume() {
        super.onResume();

        BusProvider.getInstance().register(this);
        homepagePresenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();

        BusProvider.getInstance().unregister(this);
    }

    @Override
    public void onStop() {
        super.onStop();

        homepagePresenter.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        homepagePresenter.onDestroy();
    }

    @Override
    public void initContentView() {
        floatActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BusProvider.getInstance().post(new FABClickedEvent());
            }
        });
    }

    @Override
    public void setupToolBar() {
        toolbar.setTitle(R.string.app_name);
        // 设置 TooBar 的副标题
//        toolbar.setSubtitle("test");
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
        // Navigation Icon 要设定在 setSupoortActionBar 才有作用，否则会出现 back button
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainView.openDrawer();
            }
        });
    }

    @Override
    public void setupViewPagerAdapter() {
        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        IApplicationModel applicationModel = mainView.getApplicationModel();
        if (applicationModel != null) {
            Map<String, String> categories = applicationModel.getCategories();
            for (String key : categories.keySet()) {
                addHomeTab(key, categories.get(key));
            }
            setupViewPager();
        } else {
            showMessage("error");
        }
    }

    @Override
    public void setupViewPager() {
        if (viewPagerAdapter != null) {
            viewPager.setAdapter(viewPagerAdapter);
            tablayout.setupWithViewPager(viewPager);
            // setupWithViewPager 中已经设置了相应的 OnTabSelectedListener，此处修改是为了实现功能的自我定制
            tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    viewPager.setCurrentItem(tab.getPosition(), false);
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {

                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
        } else {
            Log.d(TAG, "viewPagerAdapter is null");
        }
    }

    @Override
    public void addHomeTab(String category, String title) {
        viewPagerAdapter.addItem(HomeTabFragment.newInstance(category, null), title);
    }

    @Override
    public void deleteHomeTab(String key) {

    }

    @Override
    public void showFABtn() {
        floatActionButton.show();
    }

    @Override
    public void hideFABtn() {
        floatActionButton.hide();
    }

    @Override
    public void setOnFABListener(OnFABClickedListener listener) {
        this.onFABClickedListener = listener;
    }

    @Override
    public IMainView getMainView() {
        return mainView;
    }

    @Override
    public void showMessage(String msg) {

    }
}
