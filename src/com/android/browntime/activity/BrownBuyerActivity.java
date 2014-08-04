package com.android.browntime.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.browntime.BrownOrderItemListFragment;
import com.android.browntime.JSONRequest;
import com.android.browntime.R;
import com.android.browntime.dataLab.OrderLab;
import com.android.browntime.model.BrownBuyer;
import com.android.browntime.model.BrownOrder;

import org.springframework.web.client.RestTemplate;

/**
 * Created by kimsanghwan on 7/30/2014.
 */
public class BrownBuyerActivity extends ActionBarActivity {


    private BrownOrder mCurrentOrder;
    private View deliveryView;
    int orderType;
    LinearLayout detailAndConfirmationView;
    BrownOrderItemListFragment mBrownOrderItemListFragment;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        orderType = getIntent().getIntExtra("orderType", 1);

        mCurrentOrder = OrderLab.get(this).getLastOrder();
        setContentView(R.layout.activity_buyer_form);


        detailAndConfirmationView = (LinearLayout)findViewById(R.id.order_confirmation_and_detail_wrap);
        LayoutInflater inflater = LayoutInflater.from(BrownBuyerActivity.this);
        View phoneConfirmationView = inflater.inflate(R.layout.form_order_details, null);
        detailAndConfirmationView.addView(phoneConfirmationView);


        TextView totalPriceView = (TextView)findViewById(R.id.order_menu_price_total);
        totalPriceView.setText(String.valueOf(mCurrentOrder.getmPrice()));

        if (orderType == 3) {
            LinearLayout addressView = (LinearLayout)findViewById(R.id.order_address_view);
            deliveryView = getLayoutInflater().inflate(R.layout.delivery_layout, null);
            addressView.addView(deliveryView);
        }

        mBrownOrderItemListFragment = new BrownOrderItemListFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragmentCartList, mBrownOrderItemListFragment);
        transaction.commit();


        Button orderCompleteButton = (Button)findViewById(R.id.order_complete_button);
        orderCompleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (orderType == 3) {
                    TextView userAddressInfo = (TextView) findViewById(R.id.order_info_address);
                    mCurrentOrder.setmAddress(userAddressInfo.getText().toString());
                }
                TextView userName = (TextView)findViewById(R.id.order_info_user_name);
                TextView userCellNumber = (TextView)findViewById(R.id.order_info_user_phone);
                mCurrentOrder.setmBuyerName(userName.getText().toString());
                mCurrentOrder.setmBuyerCellNumber(Integer.valueOf(userCellNumber.getText().toString()));


                //if session - first time
                detailAndConfirmationView.removeAllViews();
                LayoutInflater inflater = LayoutInflater.from(BrownBuyerActivity.this);
                View phoneConfirmationView = inflater.inflate(R.layout.form_phone_confirmation, null);
                detailAndConfirmationView.addView(phoneConfirmationView);
                TextView phoneNum = (TextView)findViewById(R.id.order_phone_label);
                phoneNum.setText(String.valueOf(mCurrentOrder.getmBuyerCellNumber()));
                findViewById(R.id.confirmation_request).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new HttpRequestSendSMS().execute(mCurrentOrder.getmBuyerCellNumber());
                    }
                });

//                SmsManager sms = SmsManager.getDefault();
//                sms.sendTextMessage("01099155894", null, "hello", null, null);
//
//                new HttpRequestTask().execute();
            }
        });

    }

//    public void confirmation_request() {
//        int i = 9;
//        i = i+1;
//        new HttpRequestTask().execute();
//    }

    private class HttpRequestSendSMS extends AsyncTask<Integer, Void, Void> {
        @Override
        protected Void doInBackground(Integer... phoneNums) {
            try {
                int phoneNum = phoneNums[0];
                final String url = "http://10.0.2.2:8080/BrownTime/json/requestSMS";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.setMessageConverters(new JSONRequest().getMessageConverters());
//                restTemplate.postForObject(url, String.class);

                BrownBuyer buyer = new BrownBuyer();
                buyer.setmBuyerName(mCurrentOrder.getmBuyerName());
                buyer.setmBuyerCellNumber(mCurrentOrder.getmBuyerCellNumber());
                buyer.setmSMSNumber(1111);
                BrownBuyer buyerReturned = restTemplate.postForObject(url, buyer, BrownBuyer.class);
//                return new BrownOrder();
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

//        @Override
//        protected void onPostExecute(BrownOrder greeting) {
//        }

    }

    private class HttpRequestOrderComplete extends AsyncTask<Void, Void, BrownOrder> {
        @Override
        protected BrownOrder doInBackground(Void... params) {
            try {

//                final String url = "http://browntime123.cafe24.com/json/addOrder";
                final String url = "http://10.0.2.2:8080/BrownTime/json/addOrder";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.setMessageConverters(new JSONRequest().getMessageConverters());

                BrownOrder order = restTemplate.postForObject(url, mCurrentOrder, BrownOrder.class);

                return order;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(BrownOrder greeting) {
            Intent i = new Intent(BrownBuyerActivity.this, BrownOrderActivity.class);
            OrderLab.get(BrownBuyerActivity.this).addOrder(mCurrentOrder);
            startActivity(i);
        }

    }


}
