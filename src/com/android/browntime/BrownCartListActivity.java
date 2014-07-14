package com.android.browntime;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class BrownCartListActivity extends SingleFragmentActivity {
	
	private static final String DIALOG_TIME = "timePicker";
	private static final int REQUEST_TIME = 0;

	private BrownOrder mCurrentOrder;
	private View mTimeContainer;
	private TextView mCartSum;
	private Button mTakeOut, mForHere, mDelivery, mCheckoutOrderTimeButton;
	
	@Override
	protected Fragment createFragment() {
		return new BrownCartListFragment();
	}
	
	 public void onCreate(Bundle savedInstanceState) {
	    	
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_cart);		
	        
	        
	        ActionBar actionBar = getSupportActionBar();
	        actionBar.setDisplayHomeAsUpEnabled(true);
	        
	        mCartSum = (TextView)findViewById(R.id.cart_sum);
	        mCartSum.setText(String.valueOf(CartLab.get(this).getPriceTotal()));
	        
	        mTakeOut = (Button)findViewById(R.id.checkoutTakeOut);
	        mTakeOut.setText(R.string.checkout_take_out); 
	        mTakeOut.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					orderComplete(1);					
				}
			});
	        
	        mForHere = (Button)findViewById(R.id.checkoutForHere);
	        mForHere.setText(R.string.checkout_for_here); 
	        mForHere.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					orderComplete(2);					
				}
			});
	        
	        mDelivery = (Button)findViewById(R.id.checkoutDelivery);
	        mDelivery.setText(R.string.checkout_delivery);
	        mDelivery.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					orderComplete(3);					
				}
			});
	        
	        mTimeContainer = (View)findViewById(R.id.checkoutOrderTimeContainer);
	        
	        mCheckoutOrderTimeButton = (Button)findViewById(R.id.checkoutOrderTimeButton);
	        mCheckoutOrderTimeButton.setText(R.string.cart_timer_label);
	        mCheckoutOrderTimeButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					DialogFragment newFragment = new TimePickerFragment();
				    newFragment.show(getSupportFragmentManager(), DIALOG_TIME);
				}
			});
	 }
	 
	 public void onTimeSetValue(TimePicker view, int hourOfDay, int minute) {
		 mCheckoutOrderTimeButton.setText("today time" + hourOfDay + ":" + minute);
	 }
	 
	 public void onRadioButtonClicked(View view) {
         // Is the button now checked?
         boolean checked = ((RadioButton) view).isChecked();
         
         // Check which radio button was clicked
         switch(view.getId()) {
             case R.id.checkoutOrderInAdvance:
                 if (checked) {
  					Toast.makeText(this, R.string.checkout_order_in_advance, Toast.LENGTH_SHORT).show();
  					mTimeContainer.setVisibility(View.VISIBLE);
//  					mCurrentOrder.setType(1);
                 }
                 break;
             case R.id.checkoutOrderNow:
                 if (checked) {
  					Toast.makeText(this, R.string.checkout_order_now, Toast.LENGTH_SHORT).show();
  					mTimeContainer.setVisibility(View.GONE);
//  					mCurrentOrder.setType(2);
                 }
                 break;
         }
     }
	 public void orderComplete(int type) {
//		 mCurrentOrder.setCarts();
	 }
	public void showTimePickerDialog(View v) {
		    
	}
}
