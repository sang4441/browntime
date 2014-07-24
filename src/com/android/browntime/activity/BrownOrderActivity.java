package com.android.browntime.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import com.android.browntime.BrownOrderItemListFragment;
import com.android.browntime.OrderLab;
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
		        mOrderType.setText("For here");
	        } else {
		        mOrderType.setText("To go");
	        }
	       
//			Intent intent = getIntent();
//			currentOrder = intent.getParcelableExtra("orderObject");

			mBrownOrderItemListFragment = new BrownOrderItemListFragment();
//			Bundle args = new Bundle();
//            args.putSerializable("orderObject", (Serializable)currentOrder);
//            mBrownOrderItemListFragment.setArguments(args);
//			
			FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
	        transaction.add(R.id.fragmentCartList, mBrownOrderItemListFragment);
	        transaction.commit();
	 }
}
