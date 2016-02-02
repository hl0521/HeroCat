package me.uteacher.www.herocat.module.homepage.viewPagerAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HL0521 on 2016/1/20.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> fragmentTitleList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public List<Fragment> getFragmentArrayList() {
        return fragmentList;
    }

    public List<String> getFragmentTitleList() {
        return fragmentTitleList;
    }

    /**
     * ViewPager中添加一个新的 Fragment，该 Fragment 在 Tablayout 中的显示的名称为 title
     *
     * @param fragment
     * @param title
     */
    public void addItem(Fragment fragment, String title) {
        fragmentList.add(fragment);
        fragmentTitleList.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitleList.get(position);
    }
}
