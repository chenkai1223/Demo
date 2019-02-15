package com.example.z01.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.andy.library.ChannelBean;

import java.util.ArrayList;
import java.util.List;

public class Upadapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentList = new ArrayList<Fragment>();
    private List<ChannelBean> stringList = new ArrayList<ChannelBean>();

    public Upadapter(FragmentManager fm, List<Fragment> fragmentList, List<ChannelBean> stringList) {
        super(fm);
        this.fragmentList = fragmentList;
        this.stringList = stringList;
    }

    public Upadapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return stringList.get(position).getName();
    }
}
