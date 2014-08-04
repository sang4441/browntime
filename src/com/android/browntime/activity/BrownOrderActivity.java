package com.android.browntime.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import com.android.browntime.BrownOrderItemListFragment;
import com.android.browntime.dataLab.OrderLab;
import com.android.browntime.R;
import com.android.browntime.model.BrownOrder;

public class BrownOrderActivity extends ActionBarActivity {

	private TextView mOrderType;
	
	BrownOrderItemListFragment mBrownOrderItemListFragment;
	BrownOrder currentOrder;
	 
	public void onCreate(Bundle savedInstanceState) {

	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_order);
	        
	        mOrderType = (TextView)findViewById(R.id.order_menu_type);

	        int orderType = OrderLab.get(this).getLastOrder().getmType();
	        if (orderType == 1) {
		        mOrderType.setText(R.string.checkout_take_out);
	        } else if (orderType == 2) {
		        mOrderType.setText(R.string.checkout_for_here);
	        } else {
                mOrderType.setText(R.string.checkout_delivery);
            }

			mBrownOrderItemListFragment = new BrownOrderItemListFragment();
			FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
	        transaction.add(R.id.fragmentCartList, mBrownOrderItemListFragment);
	        transaction.commit();
	 }
}
