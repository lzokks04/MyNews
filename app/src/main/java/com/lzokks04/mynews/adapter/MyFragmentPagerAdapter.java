package com.lzokks04.mynews.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Liu on 2016/9/4.
 */
public class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> viewList;
    private String [] mTitle = {
            "头条","社会","国内","国际","娱乐","体育","军事","科技","财经","时尚"
    };

    public MyFragmentPagerAdapter(FragmentManager fm,List<Fragment> views) {
        super(fm);
        this.viewList = views;
    }

    @Override
    public Fragment getItem(int position) {
        return viewList.get(position);
    }

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle[position];
    }
}
