package com.android.browntime;

import java.io.Serializable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;

public class BrownOrderActivity extends ActionBarActivity {

	BrownOrderItemListFragment mBrownOrderItemListFragment; 
	BrownOrder currentOrder;
	 public void onCreate(Bundle savedInstanceState) {

	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_order);		
	       
			Intent intent = getIntent();
			currentOrder = intent.getParcelableExtra("orderObject");

			mBrownOrderItemListFragment = new BrownOrderItemListFragment();
			Bundle args = new Bundle();
            args.putSerializable("orderObject", (Serializable)currentOrder);
            mBrownOrderItemListFragment.setArguments(args);
			
			FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
	        transaction.add(R.id.fragmentCartList, mBrownOrderItemListFragment);
	        transaction.commit();
	 }
}
