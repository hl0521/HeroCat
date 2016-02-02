package me.uteacher.www.herocat.module.homepage.homeTab.recyclerViewAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;

import java.util.List;

import me.uteacher.www.herocat.R;
import me.uteacher.www.herocat.model.instagram.IInstagramModel;
import me.uteacher.www.herocat.model.instagram.InstagramModel;

/**
 * Created by HL0521 on 2016/1/21.
 */
public class UltimateRecyclerViewAdapter extends UltimateViewAdapter<UltimateViewHolder> {

    private List<IInstagramModel> itemList;
    private ICardPresenter cardPresenter = null;

    public UltimateRecyclerViewAdapter(List<IInstagramModel> list) {
        this.itemList = list;
    }

    public void setCardPresenter(ICardPresenter cardPresenter) {
        this.cardPresenter = cardPresenter;
    }

    public ICardPresenter getCardPresenter() {
        return cardPresenter;
    }

    /**
     * 添加数据集到 Adapter 中
     *
     * @param list 待添加的数据集
     */
    public void addItem(List<IInstagramModel> list) {
        itemList.addAll(list);
    }

    /**
     * 清空数据集（eg：应用到清除缓存）
     */
    public void clearItem() {
        itemList.clear();
    }

    // 下面这个暂时应该没有用，暂且不管，以后有用再改
    @Override
    public UltimateViewHolder getViewHolder(View view) {
        return new UltimateViewHolder(view, false);
    }

    @Override
    public UltimateViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_instagram, parent, false);
        UltimateViewHolder ultimateViewHolder = new UltimateViewHolder(view, true);
        if (cardPresenter != null) {
            cardPresenter.onCardViewCreated(ultimateViewHolder);
        }
        return ultimateViewHolder;
    }

    @Override
    public int getAdapterItemCount() {
        return itemList.size();
    }

    @Override
    public long generateHeaderId(int position) {
        return -1;
    }


    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public void onBindViewHolder(UltimateViewHolder holder, int position) {
        if (cardPresenter != null) {
            if (itemList.size() > 0) {
                IInstagramModel instagramModel = getInstagramModel(position);
                if (instagramModel != null) {
                    cardPresenter.onCardViewBind(holder, instagramModel);
                }
            }
        }
    }

    private IInstagramModel getInstagramModel(int position) {
        if (customHeaderView != null) {
            position = position - 1;
        }
        if ((position >= 0) && (position < itemList.size())) {
            return itemList.get(position);
        }
        return null;
    }

}
