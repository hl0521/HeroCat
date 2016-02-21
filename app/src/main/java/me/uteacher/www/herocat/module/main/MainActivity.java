package me.uteacher.www.herocat.module.main;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.view.menu.MenuWrapperFactory;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.avos.avoscloud.AVAnalytics;
import com.avos.sns.SNS;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.uteacher.www.herocat.R;
import me.uteacher.www.herocat.app.BaseActivity;
import me.uteacher.www.herocat.config.DefaultAppConfig;
import me.uteacher.www.herocat.model.ModelFactory;
import me.uteacher.www.herocat.model.application.IApplicationModel;
import me.uteacher.www.herocat.model.instagram.IInstagramModel;
import me.uteacher.www.herocat.model.user.IUserModel;
import me.uteacher.www.herocat.module.favourite.FavouriteFragment;
import me.uteacher.www.herocat.module.homepage.HomepageFragment;
import me.uteacher.www.herocat.module.setting.SettingFragment;
import me.uteacher.www.herocat.module.signin.login.LoginFragment;
import me.uteacher.www.herocat.util.DownloadHelper.AppDownloadManager;
import me.uteacher.www.herocat.util.SNSUtil.ISNSUtil;
import me.uteacher.www.herocat.util.SNSUtil.SNSUtil;
import me.uteacher.www.herocat.widget.CircleImageView;

/**
 * Created by HL0521 on 2016/1/19.
 */
public class MainActivity extends BaseActivity implements IMainView, NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";

    @Bind(R.id.main_container)
    FrameLayout mainContainer;
    @Bind(R.id.main_navigation)
    NavigationView mainNavigation;
    @Bind(R.id.main_drawer)
    DrawerLayout mainDrawer;

    private IUserModel userModel;
    private IApplicationModel applicationModel;

    private IMainPresenter mainPresenter;

    private TextView userName;
    private CircleImageView userPortrait;

    private MenuItem navigateToSignin;
    private MenuItem navigateToHomepage;
    private MenuItem navigateToFavourite;
    private MenuItem navigateToSetting;
    private MenuItem navigateToQuit;

    // app 第一次打开，进入到Homepage：true；后退 或者 点击首页 等方式进入到 Homepage：false
    private boolean isFisrtToHomepage = true;

    private MaterialDialog logoutDialog;
    private MaterialDialog needLoginDialog;
    private MaterialDialog shareToSnsDialog;

    private ISNSUtil snsUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mainPresenter = new MainPresenterImpl(this);
        mainPresenter.onCreate();

        snsUtil = new SNSUtil(this);
        snsUtil.onCreate(savedInstanceState, getIntent());

        AVAnalytics.trackAppOpened(getIntent());
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainPresenter.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainPresenter.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.onDestroy();
        snsUtil.onDestroy();
    }

    // 此处是为了实现功能：点击 EditText 区域外的其它地方，隐藏 软键盘
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();

            if (isShouldHideInputKeyboard(view, ev)) {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (inputMethodManager != null) {
                    inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        }

        return super.dispatchTouchEvent(ev);
    }

    private boolean isShouldHideInputKeyboard(View view, MotionEvent event) {
        if ((view != null) && (view instanceof EditText)) {
            int[] leftTop = {0, 0};

            view.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int right = left + view.getWidth();
            int bottom = top + view.getHeight();

            return !((event.getX() > left) && (event.getX() < right) && (event.getY() > top) && event.getY() < bottom);
        }
        return true;
    }

    @Override
    public void initContentView() {
        mainNavigation.setNavigationItemSelectedListener(this);
        Menu menu = mainNavigation.getMenu();
        navigateToSignin = menu.findItem(R.id.signin);
        navigateToHomepage = menu.findItem(R.id.homepage);
        navigateToFavourite = menu.findItem(R.id.favourite);
        navigateToSetting = menu.findItem(R.id.setting);
        navigateToQuit = menu.findItem(R.id.quit);

        View headView = mainNavigation.getHeaderView(0);
        userName = (TextView) headView.findViewById(R.id.user_name);
        userPortrait = (CircleImageView) headView.findViewById(R.id.user_portrait);

        // TODO: 2016/1/27 下面这段代码，应该可以提取出来，制作成一个工具类！
        // 从缓存文件中读取数据，初始化 ApplicationModel
        File file = new File(DefaultAppConfig.TEXT_FILE, "ApplicationModel.txt");
        if (file.exists()) {
            BufferedReader bufferedReader = null;
            try {
                bufferedReader = new BufferedReader(new FileReader(file));
                StringBuilder readString = new StringBuilder();
                String currentLine;
                while ((currentLine = bufferedReader.readLine()) != null) {
                    readString.append(currentLine);
                }
                JSONObject jsonObject = new JSONObject(readString.toString());
                applicationModel = ModelFactory.createApplicationModel(jsonObject.toString());
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            } finally {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void openDrawer() {
        mainDrawer.openDrawer(GravityCompat.START);
    }

    @Override
    public void closeDrawer() {
        mainDrawer.closeDrawers();
    }

    @Override
    public void navigateToSignin() {
//        navigateToSignin.setChecked(false);
        getSupportFragmentManager().beginTransaction().addToBackStack("HomepageFragment")
                .replace(R.id.main_container, LoginFragment.newInstance(null, null)).commit();
    }

    @Override
    public void navigateToHomepage() {
        if (isFisrtToHomepage) {
            isFisrtToHomepage = false;
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main_container, HomepageFragment.newInstance(null, null)).commit();
        } else {
            getSupportFragmentManager().popBackStack("HomepageFragment", FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    @Override
    public void navigateToFavourite() {
        if (userModel != null) {
            getSupportFragmentManager().beginTransaction().addToBackStack(null)
                    .replace(R.id.main_container, FavouriteFragment.newInstance(null, null)).commit();
        } else {
            showNeedLoginDialog("请先登陆", "该操作需要用户登陆", "登陆"
                    , "取消", new IDialogCallback() {
                @Override
                public void onPositiveBtnClicked() {
                    navigateToSignin();
                }

                @Override
                public void onNegativeBtnClicked() {

                }
            });
        }
    }

    @Override
    public void navigateToSetting() {
        getSupportFragmentManager().beginTransaction().addToBackStack(null)
                .replace(R.id.main_container, SettingFragment.newInstance(null, null)).commit();
    }

    @Override
    public void nagigateToQuit() {
        finish();
        System.runFinalization();
        System.exit(0);
    }

    @Override
    public void setUserPortrait(Bitmap bitmap) {
        userPortrait.setImageBitmap(bitmap);
    }

    @Override
    public void setUserPortrait(String uri) {
        userPortrait.setImageURI(Uri.parse(uri));
    }

    @Override
    public void setUserPortrait(int resId) {
        userPortrait.setImageResource(resId);
    }

    @Override
    public void setUserName(String userName) {
        this.userName.setText(userName);
    }

    @Override
    public void setUserModel(IUserModel userModel) {
        this.userModel = userModel;
        mainPresenter.onSetUserModel();
    }

    @Override
    public IUserModel getUserModel() {
        return userModel;
    }

    @Override
    public void setApplicationModel(IApplicationModel applicationModel) {
        this.applicationModel = applicationModel;
    }

    @Override
    public IApplicationModel getApplicationModel() {
        return applicationModel;
    }

    @Override
    public void setLoginMenuText(String text) {
        navigateToSignin.setTitle(text);
    }

    @Override
    public void setLoginMenuText(int id) {
        navigateToSignin.setTitle(R.string.navigation_menu_signout);
    }

    private MaterialDialog createDialog(String title, String message, String positive, String negative
            , final IDialogCallback dialogCallback) {
        return new MaterialDialog.Builder(this)
                .title(title)
                .content(message)
                .positiveText(positive)
                .negativeText(negative)
                .onAny(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                        switch (which) {
                            case POSITIVE:
                                dialogCallback.onPositiveBtnClicked();
                                break;
                            case NEUTRAL:
                                break;
                            case NEGATIVE:
                                dialogCallback.onNegativeBtnClicked();
                                break;
                            default:
                        }
                    }
                }).show();
    }

    @Override
    public void showNeedLoginDialog(String title, String message, String positive, String negative
            , IDialogCallback dialogCallback) {
        if (needLoginDialog == null) {
            needLoginDialog = createDialog(title, message, positive, negative, dialogCallback);
            needLoginDialog.show();
        } else {
            needLoginDialog.show();
        }
    }

    @Override
    public void showLogoutDialog(String title, String message, String positive, String negative
            , IDialogCallback dialogCallback) {
        if (logoutDialog == null) {
            logoutDialog = createDialog(title, message, positive, negative, dialogCallback);
            logoutDialog.show();
        } else {
            logoutDialog.show();
        }
    }

    @Override
    public void showShareToSnsDialog(String title, String negative, final IInstagramModel instagramModel
            , final IShareToSNSCallback callback) {
        if (shareToSnsDialog == null) {
            shareToSnsDialog = new MaterialDialog.Builder(this)
                    .title(title)
                    .customView(R.layout.dialog_share_to_sns, false)
                    .negativeText(negative)
                    .onNegative(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            dialog.dismiss();
                        }
                    })
                    .show();
        } else {
            shareToSnsDialog.setTitle(title);
            shareToSnsDialog.setActionButton(DialogAction.NEGATIVE, negative);
            shareToSnsDialog.show();
        }

        View view = shareToSnsDialog.getCustomView();
        ImageButton shareToWechat = (ImageButton) view.findViewById(R.id.btn_share_wechat);
        shareToWechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareToSnsDialog.dismiss();
                callback.onShareToWechatClicked(instagramModel);
            }
        });

        ImageButton shareToWechatTimeline = (ImageButton) view.findViewById(R.id.btn_share_wechat_timeline);
        shareToWechatTimeline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareToSnsDialog.dismiss();
                callback.onShareToWechatTimelineClicked(instagramModel);
            }
        });

        ImageButton shareToQQ = (ImageButton) view.findViewById(R.id.btn_share_qq);
        shareToQQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareToSnsDialog.dismiss();
                callback.onShareToQQClicked(instagramModel);
            }
        });

        ImageButton shareToWeibo = (ImageButton) view.findViewById(R.id.btn_share_weibo);
        shareToWeibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareToSnsDialog.dismiss();
                callback.onShareToWeiboClicked(instagramModel);
            }
        });
    }

    @Override
    public void shareToWechat(String webUrl, String title, String description, InputStream imageInput, boolean timeline) {
        snsUtil.shareToWechat(webUrl, title, description, imageInput, timeline);
    }

    @Override
    public void shareToQQ(String webUrl, String title, String description, String imageUrl, String appName) {

    }

    @Override
    public void shareToWeibo(String webUrl, String title, String description, InputStream imageInput) {

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.signin:
                mainPresenter.onNavigationSigninSelected();
                break;
            case R.id.homepage:
                mainPresenter.onNavigationHomepageSelected();
                break;
            case R.id.favourite:
                mainPresenter.onNavigationFavouriteSelected();
                break;
            case R.id.setting:
                mainPresenter.onNavigationSettingSelected();
                break;
            case R.id.quit:
                mainPresenter.onNavigationQuitSelected();
                break;
            default:
                break;
        }
        return true;
    }
}
