package com.android.browntime.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.browntime.BrownOrderItemListFragment;
import com.android.browntime.JSONRequest;
import com.android.browntime.R;
import com.android.browntime.dataLab.OrderLab;
import com.android.browntime.model.BrownOrder;

import org.springframework.web.client.RestTemplate;

/**
 * Created by kimsanghwan on 7/30/2014.
 */
public class BrownBuyerActivity extends ActionBarActivity {


    private BrownOrder mCurrentOrder;
    private View deliveryView;
    int orderType;
    Spinner spinner;
    LinearLayout detailWrap;
    BrownOrderItemListFragment mBrownOrderItemListFragment;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        orderType = getIntent().getIntExtra("orderType", 1);

        mCurrentOrder = OrderLab.get(this).getLastOrder();
        setContentView(R.layout.activity_buyer_form);


        detailWrap = (LinearLayout)findViewById(R.id.order_detail_wrap);
        LayoutInflater inflater = LayoutInflater.from(BrownBuyerActivity.this);
        View orderDetailView = inflater.inflate(R.layout.form_order_details, null);
        detailWrap.addView(orderDetailView);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        TextView totalPriceView = (TextView)findViewById(R.id.order_menu_price_total);
        totalPriceView.setText(String.valueOf(mCurrentOrder.getmPrice()));

        if (orderType == 3) {
            LinearLayout addressView = (LinearLayout)findViewById(R.id.order_address_view);
            deliveryView = getLayoutInflater().inflate(R.layout.delivery_layout, null);
            addressView.addView(deliveryView);

            spinner = (Spinner) findViewById(R.id.predefined_address_spinner);

// Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.predefined_address_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
            spinner.setAdapter(adapter);
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
                    String selectedItem = spinner.getSelectedItem().toString();
                    mCurrentOrder.setmAddress(selectedItem+ "\t" + userAddressInfo.getText().toString());
                }
                TextView userName = (TextView)findViewById(R.id.order_info_user_name);
                TextView userCellNumber = (TextView)findViewById(R.id.order_info_user_phone);
                if (userName.getText().toString().isEmpty() || userCellNumber.getText().toString().isEmpty()) {
                    Toast.makeText(BrownBuyerActivity.this, "fill all your information", Toast.LENGTH_SHORT).show();
                } else {

                    mCurrentOrder.setmBuyerName(userName.getText().toString());
                    mCurrentOrder.setmBuyerCellNumber(userCellNumber.getText().toString());


//                    Intent i = new Intent(BrownBuyerActivity.this, BrownOrderActivity.class);
//                    startActivity(i);
                    //if session add fragment

                    //else complete order

//                SmsManager sms = SmsManager.getDefault();
//                sms.sendTextMessage("01099155894", null, "hello", null, null);
//
                    new HttpRequestOrderComplete().execute();
                }

            }
        });

    }

//    public void confirmation_request() {
//        int i = 9;
//        i = i+1;
//        new HttpRequestTask().execute();
//    }



    private class HttpRequestOrderComplete extends AsyncTask<Void, Void, BrownOrder> {
        @Override
        protected BrownOrder doInBackground(Void... params) {
            try {

                final String url = "http://browntime123.cafe24.com/json/addOrder";
//                final String url = "http://10.0.2.2:8080/BrownTime/json/addOrder";
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
//            OrderLab.get(BrownBuyerActivity.this).addOrder(mCurrentOrder);
            startActivity(i);
        }
    }
}
