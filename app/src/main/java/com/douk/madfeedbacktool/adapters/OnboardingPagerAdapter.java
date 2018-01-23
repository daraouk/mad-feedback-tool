package com.douk.madfeedbacktool.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class OnboardingPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> sectionList = new ArrayList<>();
    private final List<String> sectionTitleList = new ArrayList<>();
    private final List<String> sectionDescriptionList = new ArrayList<>();

    public OnboardingPagerAdapter(FragmentManager fm) {
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
    public void addSection(Fragment fragment, String title, String desc) {
        sectionList.add(fragment);
        sectionTitleList.add(title);
        sectionDescriptionList.add(desc);
    }
}