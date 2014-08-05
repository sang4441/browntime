package com.android.browntime.activity;

import android.app.ActionBar;
import android.app.AlarmManager;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.browntime.BrownMenuListFragment;
import com.android.browntime.BrownTestFragment;
import com.android.browntime.DemoCollectionPagerAdapter;
import com.android.browntime.JSONRequest;
import com.android.browntime.R;
import com.android.browntime.dataLab.CartLab;
import com.android.browntime.dataLab.MenuLab;
import com.android.browntime.model.BrownCategory;
import com.android.browntime.model.BrownMenu;
import com.android.browntime.service.BrownOrderStatusService;

import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * Created by kimsanghwan on 7/30/2014.
 */
public class DrawerActivity extends ActionBarActivity {
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mPlanetTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    Fragment fragmentDrawer;

    DemoCollectionPagerAdapter mDemoCollectionPagerAdapter;
    ViewPager mViewPager;
    TextView mGoToCart, mGoToCartNum;
    View v;
    View menuView;
    boolean isCartEmpty;

    public static int TAB_NUM;

    public static final String SERVICE_KEY = "brown_admin_order_service";

    private BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
//            int newOrderNum = bundle.getInt(SERVICE_KEY);
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);

        //set up service for order status update
        Calendar cal = Calendar.getInstance();
        Intent intent = new Intent(this, BrownOrderStatusService.class);
        PendingIntent pintent = PendingIntent.getService(this, 0, intent, 0);
        AlarmManager alarm = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarm.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 30*1000, pintent);


        mPlanetTitles = getResources().getStringArray(R.array.planets_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

//        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mPlanetTitles));
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
//

        mTitle = mDrawerTitle = getTitle();
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getActionBar().setTitle(mTitle);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActionBar().setTitle(mDrawerTitle);
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        selectItem(2);

    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            ActionBar actionBar = getActionBar();
            actionBar.removeAllTabs();
            selectItem(position);
        }
    }

    private class menuFragment extends Fragment {
        private View vMenu;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
            vMenu = inflater.inflate(R.layout.activity_collection_demo, parent, false);

            return vMenu;
        }
    }

    /** Swaps fragments in the main content view */
    private void selectItem(int position) {
        // Create a new fragment and specify the planet to show based on position

        if (position == 2) {
            fragmentDrawer = new menuFragment();

//            if (MenuLab.get(DrawerActivity.this).isEmpty()) {
                new HttpRequestTaskMenu().execute();
//            } else {
//                createPager();
//            }
            new HttpRequestTask().execute();
        } else if (position == 3) {
//            Bundle args = new Bundle();
//            args.putInt(OrderHistoryFragment.ARG_DRAWER_NUMBER, position);
//            fragmentDrawer.setArguments(args);
            fragmentDrawer = new BrownTestFragment();
        }

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragmentDrawer)
                .commit();


        // Highlight the selected item, update the title, and close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(mPlanetTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }
//
    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    /* Called whenever we call invalidateOptionsMenu() */
//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        // If the nav drawer is open, hide action items related to the content view
//        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
//        menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
//        return super.onPrepareOptionsMenu(menu);
//    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
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
            MenuLab.get(DrawerActivity.this).addMenuAll(menus);
            // ViewPager and its adapters use support library
            // fragments, so use getSupportFragmentManager.

        }
    }

    private void createTabPager() {
        mViewPager = (ViewPager) fragmentDrawer.getView().findViewById(R.id.pager);
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            @Override
            public int getCount() {
                return TAB_NUM;
            }

            @Override
            public android.support.v4.app.Fragment getItem(int i) {
                android.support.v4.app.Fragment fragment = new BrownMenuListFragment();
                Bundle args = new Bundle();
                // Our object is just an integer :-P
                args.putInt(BrownMenuListFragment.MENU_TYPE, i + 1);
                fragment.setArguments(args);
                return fragment;
            }
        });



        mViewPager.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        // When swiping between pages, select the
                        // corresponding tab.
//                        if (getActionBar().getTabCount() <= position) {
//                            position = position - 1;
//                        }
                        getActionBar().setSelectedNavigationItem(position);
                    }
                });
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

            TAB_NUM = categories.size();
            createTabPager();

            isCartEmpty = CartLab.get(DrawerActivity.this).getMenus().isEmpty();
            mGoToCartNum = (TextView) fragmentDrawer.getView().findViewById(R.id.menu_cart_num);
            mGoToCartNum.setText(R.string.cart_label_empty);

            View cartView = fragmentDrawer.getView().findViewById(R.id.menu_cart_view);
            cartView.setOnClickListener(new View.OnClickListener() {



                @Override
                public void onClick(View v) {
                    if (isCartEmpty) {
                        Toast.makeText(DrawerActivity.this, R.string.cart_empty_toast, Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent(DrawerActivity.this, BrownCartListActivity.class);
                        startActivity(intent);
                    }
                }


            });

            if (!isCartEmpty) {
//                mGoToCartNum.setText(R.string.cart_label_not_empty + "(" + String.valueOf(CartLab.get(DrawerActivity.this).getMenus().size()) + ")");
                mGoToCartNum.setText(R.string.cart_label_not_empty);

            }


            mGoToCart = (TextView) fragmentDrawer.getView().findViewById(R.id.menu_cart);
            mGoToCart.setText(R.string.cart_label);




            final ActionBar actionBar = getActionBar();

            // Specify that tabs should be displayed in the action bar.
            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

            // Create a tab listener that is called when the user changes tabs.
            ActionBar.TabListener tabListener = new ActionBar.TabListener() {

                @Override
                public void onTabReselected(ActionBar.Tab tab,
                                            android.app.FragmentTransaction ft) {
                }

                @Override
                public void onTabSelected(ActionBar.Tab tab,
                                          android.app.FragmentTransaction ft) {
                    mViewPager.setCurrentItem(tab.getPosition());
                    // TODO Auto-generated method stub

                }

                @Override
                public void onTabUnselected(ActionBar.Tab tab,
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
