package me.uteacher.www.herocat.module.setting;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.uteacher.www.herocat.R;
import me.uteacher.www.herocat.app.BaseFragment;
import me.uteacher.www.herocat.module.main.IMainView;
import me.uteacher.www.herocat.module.main.MainActivity;

/**
 * Created by HL0521 on 2016/2/1.
 */
public class SettingFragment extends BaseFragment implements ISettingView {

    private static final String TAG = "SettingFragment";

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @Bind(R.id.toolbar)
    Toolbar toolbar;

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

    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void setupToolbar() {
        toolbar.setTitle(R.string.setting);

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
