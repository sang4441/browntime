package com.android.browntime;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

import com.android.browntime.dataLab.OrderLab;
import com.android.browntime.model.BrownOrder;

import java.util.List;

/**
 * Created by kimsanghwan on 7/25/2014.
 */
public class BrownUserOrderPagerActivity extends ActionBarActivity {

    private ViewPager mViewPager;
    private List<BrownOrder> mOrders;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.viewPager);
        setContentView(mViewPager);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        mOrders = OrderLab.get(this).getOrders();

        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public int getCount() {
                return mOrders.size();
            }

            @Override
            public Fragment getItem(int pos) {
                BrownOrder order = mOrders.get(pos);
                return BrownUserOrderFragment.newInstance(order.getmId());
            }
        });

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int pos) {

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });

        int menuId = (int)getIntent().getIntExtra(BrownUserOrderFragment.EXTRA_ORDER_ID, 1);
        for (int i = 0; i < mOrders.size(); i++) {
            if (mOrders.get(i).getmId()==menuId) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
