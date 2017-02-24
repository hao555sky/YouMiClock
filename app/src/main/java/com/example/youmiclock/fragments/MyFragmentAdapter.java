package com.example.youmiclock.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by hao on 17-2-16.
 */
public class MyFragmentAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList;

    private String[] tabTitles;

    public MyFragmentAdapter(FragmentManager fm, List<Fragment> fragmentList, String[] tabTitles) {
        super(fm);
        this.fragmentList = fragmentList;
        this.tabTitles = tabTitles;
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
        return tabTitles == null ? super.getPageTitle(position) : tabTitles[position];
    }
}
