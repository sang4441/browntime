package com.android.browntime;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.widget.TimePicker;

public class BrownCartListActivity extends ActionBarActivity {

	
//	
//	
//	@Override
//	protected Fragment createFragment() {
//		return new BrownCartListFragment();
//	}
	
	BrownCartListFragment mBrownCartListFragment;
	BrownCartFragment mBrownCartFragment;
	
	 public void onCreate(Bundle savedInstanceState) {

	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_cart);		
	       
//	        FragmentManager fm = getSupportFragmentManager();
//			Fragment fragment = fm.findFragmentById(R.id.fragmentContent);
//			
//			if (fragment == null) {
//				fragment = createFragment();
//				fm.beginTransaction().add(R.id.fragmentContent, fragment).commit();
//			}
			
//			
	        mBrownCartListFragment = new BrownCartListFragment();
	        mBrownCartFragment = new BrownCartFragment();
			FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
	        transaction.add(R.id.fragmentCart, mBrownCartFragment);
	        transaction.add(R.id.fragmentCartList, mBrownCartListFragment);

	        transaction.commit();
			
	        
//	        ActionBar actionBar = getSupportActionBar();
//	        actionBar.setDisplayHomeAsUpEnabled(true);
	        
	 }
	 
		
		public void onTimeSetValue(TimePicker view, int hourOfDay, int minute) {
			BrownCartFragment fragment = (BrownCartFragment)(getSupportFragmentManager().findFragmentById(R.id.fragmentCart));
			fragment.setTextTime(view, hourOfDay, minute);
		}
}
