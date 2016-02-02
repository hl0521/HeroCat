package me.uteacher.www.herocat.module.signin.login;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.uteacher.www.herocat.R;
import me.uteacher.www.herocat.app.BaseFragment;
import me.uteacher.www.herocat.module.main.IMainView;
import me.uteacher.www.herocat.module.main.MainActivity;
import me.uteacher.www.herocat.module.signin.register.RegisterFragment;
import me.uteacher.www.herocat.widget.CircleImageView;
import me.uteacher.www.herocat.widget.ClearEditText;

/**
 * Created by HL0521 on 2016/1/29.
 */
public class LoginFragment extends BaseFragment implements ILoginView {

    private static final String TAG = "LoginFragment";

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @Bind(R.id.user_portrait)
    CircleImageView userPortrait;
    @Bind(R.id.loginAccount)
    ClearEditText loginAccount;
    @Bind(R.id.loginPassword)
    ClearEditText loginPassword;
    @Bind(R.id.loginButton)
    Button loginButton;
    @Bind(R.id.loginProblem)
    TextView loginProblem;
    @Bind(R.id.loginOtherWay)
    TextView loginOtherWay;
    @Bind(R.id.loginRegister)
    TextView loginRegister;

    private IMainView mainView;
    private Context context;
    private ILoginPresenter loginPresenter;

    boolean loginAcountIsEmpty = true;
    boolean loginPasswordIsEmpty = true;

    public LoginFragment() {

    }

    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public IMainView getMainView() {
        return mainView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.context = context;
        mainView = (IMainView) getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signin_login, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (loginPresenter == null) {
            loginPresenter = new LoginPresenterImpl(this);
        }
        loginPresenter.onCreate();
    }

    @Override
    public void onResume() {
        super.onResume();

        loginPresenter.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();

        loginPresenter.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        loginPresenter.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void initContentView() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.onLoginClicked();
            }
        });
        loginButton.setClickable(false);
        loginButton.setBackgroundColor(Color.parseColor("#CCCCCC"));

        loginRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.onLoginRegisterClicked();
            }
        });

        loginProblem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.onLoginProblemClicked();
            }
        });

        loginOtherWay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.onLoginThirdPartyClicked();
            }
        });

        loginAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    loginAcountIsEmpty = false;
                } else {
                    loginAcountIsEmpty = true;
                }

                loginPresenter.onInputInfoChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        loginPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    loginPasswordIsEmpty = false;
                } else {
                    loginPasswordIsEmpty = true;
                }

                loginPresenter.onInputInfoChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void setupToolbar() {
        toolbar.setTitle(R.string.login_button);
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
    public void loginThirdParty(String username, String token, String expires, String snsType, String openId) {

    }

    @Override
    public void navigateToForgetPassword() {

    }

    @Override
    public void navigateToRegister() {
        getFragmentManager().beginTransaction().addToBackStack("LoginFragment")
                .replace(R.id.main_container, RegisterFragment.newInstance(null, null)).commit();
    }

    @Override
    public void setupLoginBtnState() {
        if (loginAcountIsEmpty || loginPasswordIsEmpty) {
            loginButton.setClickable(false);
            loginButton.setBackgroundColor(Color.parseColor("#CCCCCC"));
        } else {
            loginButton.setClickable(true);
            loginButton.setBackgroundResource(R.drawable.selector_button);
        }
    }

    @Override
    public String getAcount() {
        return loginAccount.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return loginPassword.getText().toString().trim();
    }

    @Override
    public void showMessage(String msg) {
        mainView.showMessage(msg);
    }
}
