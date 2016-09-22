package com.tcs.myfund.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tcs.myfund.fragments.LineGraphFragment;
import com.tcs.myfund.fragments.MapChartFragment;
import com.tcs.myfund.fragments.PieChartFragment;

import java.util.List;

/**
 * Created by 778363aypp on 9/22/2016.
 */

public class GraphPagerAdapter extends FragmentPagerAdapter {
    private final int NUMBER_OF_GRAPH_VIEWS = 3;
    private List<Fragment> fragments;


    public GraphPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {

        return this.fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
