package com.example.symptologger;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *     View pager adapter for sliding page view on patient view of the record.
 *     Allows user to create tabs and fragments associated with each tap.
 * </p>
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        // Get a fragment given a position
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        // Get the total number of fragments
        return mFragmentList.size();
    }

    public void addFrag(Fragment fragment, String title) {
        // Add a fragment
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Get the tab title given a position
        return mFragmentTitleList.get(position);
    }
}
