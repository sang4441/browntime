package com.android.browntime.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.browntime.BrownOrderItemListFragment;
import com.android.browntime.R;
import com.android.browntime.dataLab.CartLab;
import com.android.browntime.model.BrownOrder;

public class BrownOrderActivity extends ActionBarActivity {

	private TextView mOrderType;
	
	BrownOrderItemListFragment mBrownOrderItemListFragment;
	BrownOrder currentOrder;
	 
	public void onCreate(Bundle savedInstanceState) {

	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_order);

            Button goToSeeOrderStatusButton = (Button)findViewById(R.id.order_complete_see_status_button);



            goToSeeOrderStatusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(BrownOrderActivity.this, DrawerActivity.class);
                    i.putExtra("drawer_position", 2);
                    startActivity(i);
                }
            });

        CartLab.get(BrownOrderActivity.this).clearCart();
//	        mOrderType = (TextView)findViewById(R.id.order_menu_type);
//
//	        int orderType = OrderLab.get(this).getLastOrder().getmType();
//	        if (orderType == 1) {
//		        mOrderType.setText(R.string.checkout_take_out);
//	        } else if (orderType == 2) {
//		        mOrderType.setText(R.string.checkout_for_here);
//	        } else {
//                mOrderType.setText(R.string.checkout_delivery);
//            }
//
//			mBrownOrderItemListFragment = new BrownOrderItemListFragment();
//			FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//	        transaction.add(R.id.fragmentCartList, mBrownOrderItemListFragment);
//	        transaction.commit();
	 }
}
