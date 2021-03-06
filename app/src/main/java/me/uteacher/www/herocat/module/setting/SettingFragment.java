package me.uteacher.www.herocat.module.setting;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.uteacher.www.herocat.R;
import me.uteacher.www.herocat.app.BaseFragment;
import me.uteacher.www.herocat.module.main.IMainView;
import me.uteacher.www.herocat.module.main.MainActivity;
import me.uteacher.www.herocat.module.setting.settingWeb.SettingWebFragment;

/**
 * Created by HL0521 on 2016/2/1.
 */
public class SettingFragment extends BaseFragment implements ISettingView {

    private static final String TAG = "SettingFragment";

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.clear_cache)
    TextView clearCache;
    @Bind(R.id.check_new_version)
    TextView checkNewVersion;
    @Bind(R.id.user_feedback)
    TextView userFeedback;
    @Bind(R.id.service_terms)
    TextView serviceTerms;
    @Bind(R.id.privacy_policy)
    TextView privacyPolicy;
    @Bind(R.id.about_herocat)
    TextView aboutHerocat;

    private ISettingPresenter settingPresenter;
    private IMainView mainView;

    public SettingFragment() {

    }

    public static SettingFragment newInstance(String param1, String param2) {
        SettingFragment fragment = new SettingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mainView = (IMainView) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (settingPresenter == null) {
            settingPresenter = new SettingPresenterImpl(this);
        }
        settingPresenter.onCreate();

    }

    @Override
    public void onResume() {
        super.onResume();

        settingPresenter.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();

        settingPresenter.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        settingPresenter.onDestroy();
    }

    @Override
    public void initContentView() {
        clearCache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        checkNewVersion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        userFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().addToBackStack(null)
                        .replace(R.id.main_container, SettingWebFragment.newInstance(getResources().getString(R.string.user_feedback)
                                , "http://www.uteacher.me/instapanda/feedback/")).commit();
            }
        });
        serviceTerms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().addToBackStack(null)
                        .replace(R.id.main_container, SettingWebFragment.newInstance(getResources().getString(R.string.service_terms)
                                , "http://www.uteacher.me/instapanda/terms/")).commit();
            }
        });
        privacyPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().addToBackStack(null)
                        .replace(R.id.main_container, SettingWebFragment.newInstance(getResources().getString(R.string.privacy_policy)
                                , "http://www.uteacher.me/instapanda/privacy/")).commit();
            }
        });
        aboutHerocat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().addToBackStack(null)
                        .replace(R.id.main_container, SettingWebFragment.newInstance(getResources().getString(R.string.about_herocat)
                                , "http://www.uteacher.me/instapanda/about/")).commit();
            }
        });
    }

    @Override
    public void showMessage(String msg) {
        mainView.showMessage(msg);
    }

    @Override
    public void setupToolbar() {
        toolbar.setTitle(R.string.setting);
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
}
