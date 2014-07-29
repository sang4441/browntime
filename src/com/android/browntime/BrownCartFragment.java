package com.android.browntime;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.TimePicker;

import com.android.browntime.activity.BrownOrderActivity;
import com.android.browntime.model.BrownOrder;

import org.springframework.web.client.RestTemplate;

public class BrownCartFragment extends Fragment {
	
	private static final String DIALOG_TIME = "timePicker";
	private static final int REQUEST_TIME = 0;

	private BrownOrder mCurrentOrder;
	private View mTimeContainer;
	private TextView mCartSum, mCheckoutOrderTime;
	private Button mTakeOut, mForHere, mDelivery, mCheckoutOrderTimeButton;
	private RadioGroup mCheckoutOrderTimeRadio;
	private View v;

	public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        mCurrentOrder = new BrownOrder();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		
		 v = inflater.inflate(R.layout.fragment_cart,  parent, false);
		 	mCartSum = (TextView)v.findViewById(R.id.cart_sum);
	        mCartSum.setText(String.valueOf(CartLab.get(getActivity()).getPriceTotal()));
	        
	        mTakeOut = (Button)v.findViewById(R.id.checkoutTakeOut);
	        mTakeOut.setText(R.string.checkout_take_out); 
	        mTakeOut.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					orderComplete(1);					
				}
			});
	        
	        mForHere = (Button)v.findViewById(R.id.checkoutForHere);
	        mForHere.setText(R.string.checkout_for_here); 
	        mForHere.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					orderComplete(2);					
				}
			});
	        
	        mDelivery = (Button)v.findViewById(R.id.checkoutDelivery);
	        mDelivery.setText(R.string.checkout_delivery);
	        mDelivery.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					orderComplete(3);					
				}
			});
	        
	        mTimeContainer = (View)v.findViewById(R.id.checkoutOrderTimeContainer);
	        
	        mCheckoutOrderTime = (TextView)v.findViewById(R.id.checkoutOrderTime);
	        
	        mCheckoutOrderTimeButton = (Button)v.findViewById(R.id.checkoutOrderTimeButton);
//	        mCheckoutOrderTimeButton.setText(R.string.cart_timer_label);
	        mCheckoutOrderTimeButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					DialogFragment newFragment = new TimePickerFragment();
				    newFragment.show(getActivity().getSupportFragmentManager(), DIALOG_TIME);
				}
			});
	        
	        mCheckoutOrderTimeRadio = (RadioGroup)v.findViewById(R.id.checkoutOrderRadio);        
	        mCheckoutOrderTimeRadio.setOnCheckedChangeListener(new OnCheckedChangeListener() 
	        {
	            public void onCheckedChanged(RadioGroup group, int checkedId) {
	            	onRadioButtonClicked(v.findViewById(checkedId));
	            }
	        });
	        
	        return v;
	}

	 
	 public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.checkoutOrderInAdvance:
                if (checked) {
// 					Toast.makeText(this, R.string.checkout_order_in_advance, Toast.LENGTH_SHORT).show();
 					mTimeContainer.setVisibility(View.VISIBLE);
// 					mCurrentOrder.setType(1);
                }
                break;
            case R.id.checkoutOrderNow:
                if (checked) {
// 					Toast.makeText(this, R.string.checkout_order_now, Toast.LENGTH_SHORT).show();
 					mTimeContainer.setVisibility(View.GONE);
// 					mCurrentOrder.setType(2);
                }
                break;
        }
    }
	 public void orderComplete(int type) {
		 
		 mCurrentOrder.setmCarts(CartLab.get(getActivity()).getMenus());
		 mCurrentOrder.setmPrice(CartLab.get(getActivity()).getPriceTotal());
		 mCurrentOrder.setmType(type);
         mCurrentOrder.setmSellerId(1);

		 Intent i = new Intent(getActivity(), BrownOrderActivity.class);
		 OrderLab.get(getActivity()).addOrder(mCurrentOrder);
//		 i.putExtra("orderObject", ((Serializable)mCurrentOrder));
//		 CartLab.get(getActivity()).clearCart();
		 //delete cart
		 
        new HttpRequestTask().execute();

 		 startActivity(i);
	 }
	 
	 public void setTextTime(TimePicker view, int hourOfDay, int minute) {
		 	mCheckoutOrderTime.setText("today time" + hourOfDay + ":" + minute);
	  }
	 
	 public void refreshSum() {
		 mCartSum.setText(String.valueOf(CartLab.get(getActivity()).getPriceTotal()));
	 }

    private class HttpRequestTask extends AsyncTask<Void, Void, BrownOrder> {
        @Override
        protected BrownOrder doInBackground(Void... params) {
            try {

                final String url = "http://browntime123.cafe24.com/json/addOrder";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.setMessageConverters(new JSONRequest().getMessageConverters());

                BrownOrder greeting = restTemplate.postForObject(url, mCurrentOrder, BrownOrder.class);

                return greeting;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

//        @Override
        protected void onPostExecute(BrownOrder greeting) {
//            TextView greetingIdText = (TextView) v.findViewById(R.id.id_value);
//            TextView greetingContentText = (TextView) v.findViewById(R.id.content_value);
//            greetingIdText.setText(greeting.getId());
//            greetingContentText.setText(greeting.getContent());
        }

    }
}
