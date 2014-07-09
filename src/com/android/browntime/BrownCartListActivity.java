package com.android.browntime;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

public class BrownCartListActivity extends SingleFragmentActivity {
	
	private TextView mCartSum;
	
	
	@Override
	protected Fragment createFragment() {
		return new BrownCartListFragment();
	}
	
	 public void onCreate(Bundle savedInstanceState) {
	    	
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_cart);
			
	        mCartSum = (TextView)findViewById(R.id.cart_sum);
	        mCartSum.setText(String.valueOf(CartLab.get(this).getPriceTotal()));
	 }
}
