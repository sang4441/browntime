package com.android.browntime;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.android.browntime.fragment.BrownMenuListFragment;

//Since this is an object collection, use a FragmentStatePagerAdapter,
//and NOT a FragmentPagerAdapter.
public class DemoCollectionPagerAdapter extends FragmentStatePagerAdapter {

    public DemoCollectionPagerAdapter(FragmentManager fm) {
     super(fm);
     }

     @Override
     public Fragment getItem(int i) {
         Fragment fragment = new BrownMenuListFragment();
         Bundle args = new Bundle();
         // Our object is just an integer :-P
         args.putInt(BrownMenuListFragment.MENU_TYPE, i + 1);
         fragment.setArguments(args);
         return fragment;
     }

     @Override
     public int getCount() {
         return 3;
     }

     @Override
     public CharSequence getPageTitle(int position) {
     return "OBJECT " + (position + 1);
 }
}