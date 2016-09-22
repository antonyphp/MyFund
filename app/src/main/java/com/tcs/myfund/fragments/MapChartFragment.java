package com.tcs.myfund.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tcs.myfund.R;

/**
 * Created by 778363aypp on 9/22/2016.
 */

public class MapChartFragment extends BaseFragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.map_chart_fragment, container, false);
    }
}
