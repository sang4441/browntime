package com.android.browntime;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CollectionDemoActivity extends FragmentActivity {
    // When requested, this adapter returns a DemoObjectFragment,
    // representing an object in the collection.
    DemoCollectionPagerAdapter mDemoCollectionPagerAdapter;
    ViewPager mViewPager;
    TextView mGoToCart, mGoToCartNum;
    boolean isCartEmpty;

    public void onCreate(Bundle savedInstanceState) {
    	final ActionBar actionBar = getActionBar();
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_demo);

        isCartEmpty = CartLab.get(this).getMenus().isEmpty();
        mGoToCartNum = (TextView)findViewById(R.id.menu_cart_num);
        mGoToCartNum.setText(R.string.zero);
        
        if (!isCartEmpty) {
        	mGoToCartNum.setText(String.valueOf(CartLab.get(this).getMenus().size()));
        } 
		
        
        mGoToCart = (TextView)findViewById(R.id.menu_cart);
        mGoToCart.setText(R.string.cart_label);
		mGoToCart.setOnClickListener(new View.OnClickListener() {
		
			
			
			@Override
			public void onClick(View v) {
				if (isCartEmpty) {
					Toast.makeText(CollectionDemoActivity.this, R.string.cart_empty_toast, Toast.LENGTH_SHORT).show();
				} else {
					Intent intent = new Intent(CollectionDemoActivity.this, BrownCartListActivity.class);
				    startActivity(intent);
				}
			}
			
			
		});

      
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

        actionBar.addTab(actionBar.newTab()
                        .setText("coffee (hot)")
                        .setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab()
                .setText("coffee (ice)")
                .setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab()
                .setText("non-coffee")
                .setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab()
        		.setText("beverage")
        		.setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab()
                .setText("tea")
                .setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab()
                .setText("dessert")
                .setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab()
                .setText("brunch")
                .setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab()
                .setText("snack")
            	.setTabListener(tabListener));
       
    }
}

