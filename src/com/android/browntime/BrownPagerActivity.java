package com.android.browntime;

import java.util.ArrayList;
import java.util.UUID;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

public class BrownPagerActivity extends ActionBarActivity {

	private ViewPager mViewPager;
	private ArrayList<BrownMenu> mMenus;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mViewPager = new ViewPager(this);
		mViewPager.setId(R.id.viewPager);
		setContentView(mViewPager);
	
		
		ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
		
        
		mMenus = MenuLab.get(this).getMenus();
		
		FragmentManager fm = getSupportFragmentManager();
		mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
			@Override
			public int getCount() {
				return mMenus.size();
			}
			
			@Override
			public Fragment getItem(int pos) {
				BrownMenu menu = mMenus.get(pos);
				return BrownMenuFragment.newInstance(menu.getId());
			}
		});	
		
		mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int pos) {
				BrownMenu menu = mMenus.get(pos);
//				if (menu.getTitle() != null) {
//					setTitle(crime.getTitle());
//				}
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
		
		UUID menuId = (UUID)getIntent().getSerializableExtra(BrownMenuFragment.EXTRA_MENU_ID);
		for (int i = 0; i < mMenus.size(); i++) {
			if (mMenus.get(i).getId().equals(menuId)) {
				mViewPager.setCurrentItem(i);
				break;
			}
		}
		
	}
	
}
