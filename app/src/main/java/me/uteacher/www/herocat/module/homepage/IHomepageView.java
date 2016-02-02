package me.uteacher.www.herocat.module.homepage;

import me.uteacher.www.herocat.app.IBaseView;
import me.uteacher.www.herocat.app.IDebug;
import me.uteacher.www.herocat.module.main.IMainView;

/**
 * Created by HL0521 on 2016/1/20.
 */
public interface IHomepageView extends IBaseView, IDebug {

    /**
     * ToolBar 、 ViewPagerAdapter 、 ViewPager 初始设置
     */
    public void setupToolBar();

    public void setupViewPagerAdapter();

    public void setupViewPager();

    /**
     * @param category ViewPager 中待添加的 fragment 的类别，用于标记该 fragment
     * @param title    ViewPager 待添加的 fragment 在 Tablayout 中显示的名称
     */
    public void addHomeTab(String category, String title);

    /**
     * @param title ViewPager 中待删除的 fragment 的类别
     */
    public void deleteHomeTab(String title);

    /**
     * FloatingActionButton 显示、隐藏、设置点击事件监听器
     */
    public void showFABtn();

    public void hideFABtn();

    public void setOnFABListener(OnFABClickedListener listener);

    /**
     * 获得主 MainView，即 MainActivity
     *
     * @return 主 MainActivity
     */
    public IMainView getMainView();

}
