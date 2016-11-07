package com.ooe.fh.liftme.UI.Adapters;

/**
 * Created by Max on 04.11.2016.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ooe.fh.liftme.UI.Fragments.Fragment_StartTraining;
import com.ooe.fh.liftme.UI.Fragments.Fragment_CreateTraining;
import com.ooe.fh.liftme.UI.Fragments.Fragment_OverviewTraining;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    int mNumOfTabs;

    public SectionsPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Fragment_CreateTraining fragment = new Fragment_CreateTraining();
                return fragment;
            case 1:
                Fragment_StartTraining fragment2 = new Fragment_StartTraining();
                return fragment2;
            case 2:
                Fragment_OverviewTraining fragment3 = new Fragment_OverviewTraining();
                return fragment3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Fragment_CreateTraining";
            case 1:
                return "Fragment_StartTraining";
            case 2:
                return "Fragment_OverviewTraining";
        }
        return null;
    }
}