package com.douk.madfeedbacktool.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.douk.madfeedbacktool.activities.FeedbackActivity;
import com.douk.madfeedbacktool.fragments.QualityFragment;

import java.util.ArrayList;
import java.util.List;

public class FeedbackPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> sectionList = new ArrayList<>();
    private final List<String> sectionTitleList = new ArrayList<>();

    public FeedbackPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return sectionList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return sectionTitleList.get(position);
    }

    @Override
    public int getCount() {
        return sectionList.size();
    }

    /* Add New Fragment */
    public void addSection(Fragment fragment, String title) {
        sectionList.add(fragment);
        sectionTitleList.add(title);
    }
}