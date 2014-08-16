package com.android.browntime.activity;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.TextView;

import com.android.browntime.DemoCollectionPagerAdapter;
import com.android.browntime.JSONRequest;
import com.android.browntime.R;
import com.android.browntime.dataLab.MenuLab;
import com.android.browntime.model.BrownCategory;
import com.android.browntime.model.BrownMenu;

import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class CollectionDemoActivity extends ActionBarActivity {
    // When requested, this adapter returns a DemoObjectFragment,
    // representing an object in the collection.
    DemoCollectionPagerAdapter mDemoCollectionPagerAdapter;
    ViewPager mViewPager;
    TextView mGoToCart, mGoToCartNum;
    boolean isCartEmpty;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_demo);

        if (MenuLab.get(CollectionDemoActivity.this).isEmpty()) {
            new HttpRequestTaskMenu().execute();
        } else {
            createPager();
        }
        new HttpRequestTask().execute();
    }


    private void createPager() {
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
    }

    private class HttpRequestTaskMenu extends AsyncTask<Void, Void, List<BrownMenu>> {
        @Override
        protected List<BrownMenu> doInBackground(Void... params) {
            try {

                final String url = "http://browntime123.cafe24.com/json/getMenus/1";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.setMessageConverters(new JSONRequest().getMessageConverters());

                BrownMenu[] menus = restTemplate.getForObject(url, BrownMenu[].class);
                return Arrays.asList(menus);
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<BrownMenu> menus) {
            MenuLab.get(CollectionDemoActivity.this).addMenuAll(menus);
            createPager();
            // ViewPager and its adapters use support library
            // fragments, so use getSupportFragmentManager.

        }
    }

    private class HttpRequestTask extends AsyncTask<Void, Void, List<BrownCategory>> {
        @Override
        protected List<BrownCategory> doInBackground(Void... params) {
            try {

                final String url = "http://browntime123.cafe24.com/json/getCategories/1";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.setMessageConverters(new JSONRequest().getMessageConverters());

                BrownCategory[] categories = restTemplate.getForObject(url, BrownCategory[].class);
                return Arrays.asList(categories);
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<BrownCategory> categories) {

            final ActionBar actionBar = getActionBar();

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

            for (BrownCategory category : categories) {
                actionBar.addTab(actionBar.newTab()
                        .setText(category.getmName())
                        .setTabListener(tabListener));
            }
        }
    }
}

