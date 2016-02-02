package me.uteacher.www.herocat.module.signin.register;

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
import me.uteacher.www.herocat.widget.CircleImageView;
import me.uteacher.www.herocat.widget.ClearEditText;

/**
 * Created by HL0521 on 2016/1/29.
 */
public class RegisterFragment extends BaseFragment implements IRegisterView {

    private static final String TAG = "RegisterFragment";

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @Bind(R.id.user_portrait)
    CircleImageView userPortrait;
    @Bind(R.id.registerPhone)
    ClearEditText registerPhone;
    @Bind(R.id.registerInputCode)
    ClearEditText registerInputCode;
    @Bind(R.id.registerGetCode)
    Button registerGetCode;
    @Bind(R.id.registerSetPassword)
    ClearEditText registerSetPassword;
    @Bind(R.id.registerConfirmPassword)
    ClearEditText registerConfirmPassword;
    @Bind(R.id.registerButton)
    Button registerButton;
    @Bind(R.id.registerLogin)
    TextView registerLogin;

    private Context context;
    private IMainView mainView;

    private IRegisterPresenter registerPresenter;

    private boolean phoneIsEmpty = true;
    private boolean smsCodeIsEmpty = true;
    private boolean password1IsEmpty = true;
    private boolean password2IsEmpty = true;

    public RegisterFragment() {

    }

    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
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
        View view = inflater.inflate(R.layout.fragment_signin_register, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (registerPresenter == null) {
            registerPresenter = new RegisterPresenterImpl(this);
        }
        registerPresenter.onCreate();
    }

    @Override
    public void onResume() {
        super.onResume();
        registerPresenter.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
        registerPresenter.onStop();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        registerPresenter.onDestroy();
    }

    @Override
    public void initContentView() {
        registerPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                phoneIsEmpty = s.length() <= 0;
                registerPresenter.onInputInfoChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        registerInputCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                smsCodeIsEmpty = s.length() <= 0;
                registerPresenter.onInputInfoChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        registerSetPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                password1IsEmpty = s.length() <= 0;
                registerPresenter.onInputInfoChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        registerConfirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                password2IsEmpty = s.length() <= 0;
                registerPresenter.onInputInfoChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        registerGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerPresenter.onGetSmsCodeBtnClicked();
            }
        });
        registerGetCode.setClickable(false);
        registerGetCode.setBackgroundColor(Color.parseColor("#CCCCCC"));

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerPresenter.onRegisterBtnClicked();
            }
        });
        registerButton.setClickable(false);
        registerButton.setBackgroundColor(Color.parseColor("#CCCCCC"));

        registerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerPresenter.onNavagateToLoginBtnClicked();
            }
        });
    }

    @Override
    public void setupToolbar() {
        toolbar.setTitle(R.string.login_register);
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
    public IMainView getMainView() {
        return mainView;
    }

    @Override
    public void navigateToLogin() {
        getFragmentManager().popBackStack();
    }

    @Override
    public void setupRegisterBtnState() {
        if (phoneIsEmpty || smsCodeIsEmpty || password1IsEmpty || password2IsEmpty) {
            registerButton.setClickable(false);
            registerButton.setBackgroundColor(Color.parseColor("#CCCCCC"));
        } else {
            registerButton.setClickable(true);
            registerButton.setBackgroundResource(R.drawable.selector_button);
        }
    }

    @Override
    public void setupGetSmsBtnState() {
        if (phoneIsEmpty) {
            registerGetCode.setClickable(false);
            registerGetCode.setBackgroundColor(Color.parseColor("#CCCCCC"));
        } else {
            registerGetCode.setClickable(true);
            registerGetCode.setBackgroundResource(R.drawable.selector_button);
        }
    }

    @Override
    public String getAccountName() {
        return registerPhone.getText().toString().trim();
    }

    @Override
    public String getSmsCode() {
        return registerInputCode.getText().toString().trim();
    }

    @Override
    public String getPassword1() {
        return registerSetPassword.getText().toString();
    }

    @Override
    public String getPassword2() {
        return registerConfirmPassword.getText().toString();
    }

    @Override
    public void showMessage(String msg) {
        mainView.showMessage(msg);
    }
}
