package me.uteacher.www.herocat.module.setting.settingWeb;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.uteacher.www.herocat.R;
import me.uteacher.www.herocat.app.BaseFragment;
import me.uteacher.www.herocat.module.main.IMainView;
import me.uteacher.www.herocat.module.main.MainActivity;

/**
 * Created by HL0521 on 2016/2/19.
 */
public class SettingWebFragment extends BaseFragment implements ISettingWebView {

    private static final String TAG = "SettingWebFragment";

    private static final String ARG_TITLE = "title";
    private static final String ARG_URL = "url";

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.webview)
    WebView webview;

    private String title;
    private String url;

    private Context context;
    private IMainView mainView;

    private ISettingWebPresenter settingWebPresenter;

    public SettingWebFragment() {

    }

    public static SettingWebFragment newInstance(String title, String url) {
        SettingWebFragment fragment = new SettingWebFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putString(ARG_URL, url);
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
        View view = inflater.inflate(R.layout.fragment_setting_web, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();
        title = args.getString(ARG_TITLE);
        url = args.getString(ARG_URL);

        if (settingWebPresenter == null) {
            settingWebPresenter = new SettingWebPresenterImpl(this);
        }
        settingWebPresenter.onCreate();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();

        settingWebPresenter.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();

        settingWebPresenter.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        settingWebPresenter.onDestroy();
    }

    @Override
    public void initContentView() {
        webview.loadUrl(url);
    }

    @Override
    public void setupToolbar() {
        toolbar.setTitle(title);
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
    public void showMessage(String msg) {
        mainView.showMessage(msg);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
