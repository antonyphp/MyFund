package com.tcs.myfund;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

import com.tcs.myfund.adapter.GraphPagerAdapter;
import com.tcs.myfund.fragments.LineGraphFragment;
import com.tcs.myfund.fragments.MapChartFragment;
import com.tcs.myfund.fragments.PieChartFragment;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.TitlePageIndicator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String[] mobileArray = {"DNB Aktiv 100","DNB Norge Index","DNB Miloinvest","DNB Aktiv 100","DNB Norge Index","DNB Miloinvest"};
    FragmentPagerAdapter adapterViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_fund_activity);
//        openFragment(new LineGraphFragment(), "My Fund");

        ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        List<Fragment> fragments = getFragments();
        adapterViewPager = new GraphPagerAdapter(getSupportFragmentManager(),fragments);
        vpPager.setAdapter(adapterViewPager);

        //Bind the title indicator to the adapter
        CirclePageIndicator titleIndicator = (CirclePageIndicator) findViewById(R.id.circle_page_indicator);
        titleIndicator.setViewPager(vpPager);

        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mobileArray);

        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
//        listView.setOnTouchListener(new View.OnTouchListener() {
//            // Setting on Touch Listener for handling the touch inside ScrollView
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                // Disallow the touch request for parent scroll on touch of child view
//                v.getParent().requestDisallowInterceptTouchEvent(true);
//                return false;
//            }
//        });
        setListViewHeightBasedOnChildren(listView);
        ((ScrollView)findViewById(R.id.full_scroll_view)).smoothScrollTo(0,0);
    }



//    private void openFragment(Fragment newFragment, String title) {
//                FragmentManager fragmentManager = getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.container_body, newFragment);
//                fragmentTransaction.commitAllowingStateLoss();
//
//                // set the toolbar title
//                getSupportActionBar().setTitle(title);
//
//    }

    /**** Method for Setting the Height of the ListView dynamically.
     **** Hack to fix the issue of not showing all the items of the ListView
     **** when placed inside a ScrollView  ****/
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    private List<Fragment> getFragments() {
        List<Fragment> fList = new ArrayList<Fragment>();
        fList.add(new LineGraphFragment());
        fList.add(new MapChartFragment());
        fList.add(new PieChartFragment());
        return fList;
    }
}
