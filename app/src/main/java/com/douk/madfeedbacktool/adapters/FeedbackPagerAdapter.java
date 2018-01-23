package com.douk.madfeedbacktool.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.douk.madfeedbacktool.fragments.CategoryFragment;

public class FeedbackPagerAdapter extends FragmentPagerAdapter {

    public FeedbackPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the
        // given page and return a CategoryFragment
        return CategoryFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return 5;
    }
}