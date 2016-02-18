package me.uteacher.www.herocat.module.favourite;

import java.util.List;

import me.uteacher.www.herocat.app.IBaseView;
import me.uteacher.www.herocat.app.IDebug;
import me.uteacher.www.herocat.model.instagram.IInstagramModel;
import me.uteacher.www.herocat.module.main.IMainView;

/**
 * Created by HL0521 on 2016/2/1.
 */
public interface IFavouriteView extends IBaseView, IDebug {

    /**
     * 初始配置 ToolBar
     */
    public void setupToolbar();

    /**
     * 获取 MainView ，即 MainActivity
     *
     * @return MainView， 即 MainActivity
     */
    public IMainView getMainView();

    /**
     * 初始配置 RecyclerView
     */
    public void setupRecyclerView();

    /**
     * 初始化 RecyclerView 的 Adapter 以及 RecyclerView 的数据集
     *
     * @param items 待添加的 InstagramModel 数据集
     */
    public void initAdapterDataSet(List<IInstagramModel> items);

    /**
     * 为 RecyclerView 的 Adapter 添加数据集
     *
     * @param items 待添加的 Instagram 数据集
     */
    public void appendAdapterDataSet(List<IInstagramModel> items);

    /**
     * 清空 Adapter 的数据集
     */
    public void clearAdapterDataSet();

    /**
     * 通知 RecyclerView 的 Adapter，数据集发生了改变
     */
    public void notifyAdapterDataSetChanged();

    public void scrollToPosition(int position);

}
