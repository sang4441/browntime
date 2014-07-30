package com.android.browntime.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.browntime.JSONRequest;
import com.android.browntime.OrderLab;
import com.android.browntime.R;
import com.android.browntime.model.BrownOrder;

import org.springframework.web.client.RestTemplate;

/**
 * Created by kimsanghwan on 7/30/2014.
 */
public class BrownBuyerActivity extends ActionBarActivity {


    private BrownOrder mCurrentOrder;
    private View deliveryView;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//        mCurrentOrder = new BrownOrder();
        int orderType = getIntent().getIntExtra("orderType", 1);

        mCurrentOrder = OrderLab.get(this).getLastOrder();
        setContentView(R.layout.activity_buyer_form);

        TextView totalPriceView = (TextView)findViewById(R.id.order_menu_price_total);
        totalPriceView.setText(String.valueOf(mCurrentOrder.getmPrice()));

        if (orderType == 3) {
            LinearLayout addressView = (LinearLayout)findViewById(R.id.order_address_view);
            deliveryView = getLayoutInflater().inflate(R.layout.delivery_layout, null);
            addressView.addView(deliveryView);
        }

        Button orderCompleteButton = (Button)findViewById(R.id.order_complete_button);
        orderCompleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView userAddressInfo = (TextView)findViewById(R.id.order_info_address);
                TextView userName = (TextView)findViewById(R.id.order_info_user_name);
                TextView userCellNumber = (TextView)findViewById(R.id.order_info_user_phone);
                mCurrentOrder.setmAddress(userAddressInfo.getText().toString());
                mCurrentOrder.setmBuyerName(userName.getText().toString());
                mCurrentOrder.setmBuyerCellNumber(Integer.valueOf(userCellNumber.getText().toString()));
                new HttpRequestTask().execute();
            }
        });

    }


    private class HttpRequestTask extends AsyncTask<Void, Void, BrownOrder> {
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

//		 i.putExtra("orderObject", ((Serializable)mCurrentOrder));
//		 CartLab.get(getActivity()).clearCart();
            //delete cart


//            TextView greetingIdText = (TextView) v.findViewById(R.id.id_value);
//            TextView greetingContentText = (TextView) v.findViewById(R.id.content_value);
//            greetingIdText.setText(greeting.getId());
//            greetingContentText.setText(greeting.getContent());
        }

    }
}
