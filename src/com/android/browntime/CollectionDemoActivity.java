package com.android.browntime;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

public class CollectionDemoActivity extends FragmentActivity {
    // When requested, this adapter returns a DemoObjectFragment,
    // representing an object in the collection.
    DemoCollectionPagerAdapter mDemoCollectionPagerAdapter;
    ViewPager mViewPager;
    TextView mView1;

    public void onCreate(Bundle savedInstanceState) {
    	final ActionBar actionBar = getActionBar();
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_demo);

		
		mView1 = (TextView)findViewById(R.id.menu_cart);
		mView1.setText("Go to cart");
        // ViewPager and its adapters use support library
        // fragments, so use getSupportFragmentManager.
        mDemoCollectionPagerAdapter =
                new DemoCollectionPagerAdapter(
                        getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mDemoCollectionPagerAdapter);
        
        
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        // When swiping between pages, select the
                        // corresponding tab.
                        getActionBar().setSelectedNavigationItem(position);
                    }
                });
     // Specify that tabs should be displayed in the action bar.
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create a tab listener that is called when the user changes tabs.
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {

			@Override
			public void onTabReselected(Tab tab,
					android.app.FragmentTransaction ft) {
			}

			@Override
			public void onTabSelected(Tab tab,
					android.app.FragmentTransaction ft) {
            	mViewPager.setCurrentItem(tab.getPosition());				
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTabUnselected(Tab tab,
					android.app.FragmentTransaction ft) {
				// TODO Auto-generated method stub
				
			}
        };

        // Add 3 tabs, specifying the tab's text and TabListener
//        for (int i = 0; i < 3; i++) {
//            actionBar.addTab(
//                    actionBar.newTab()
//                            .setText("Tab " + (i + 1))
//                            .setTabListener(tabListener));
//        }
        actionBar.addTab(actionBar.newTab()
                        .setText("coffee")
                        .setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab()
                .setText("non-coffee")
                .setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab()
                .setText("side")
                .setTabListener(tabListener));
       
    }
}

