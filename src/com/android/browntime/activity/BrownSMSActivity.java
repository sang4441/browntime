package com.android.browntime.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.browntime.JSONRequest;
import com.android.browntime.R;
import com.android.browntime.dataLab.OrderLab;
import com.android.browntime.model.BrownBuyer;
import com.android.browntime.model.BrownOrder;

import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by kimsanghwan on 8/5/2014.
 */
public class BrownSMSActivity extends ActionBarActivity {
    String requiredSMSNum;
    private BrownOrder mCurrentOrder;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_phone_confirmation);

        mCurrentOrder = OrderLab.get(this).getLastOrder();

        TextView phoneNum = (TextView)findViewById(R.id.order_phone_label);
        phoneNum.setText(String.valueOf(mCurrentOrder.getmBuyerCellNumber()));

        findViewById(R.id.order_confirmation_back_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BrownSMSActivity.this, BrownBuyerActivity.class);
                startActivity(i);
            }
        });

        findViewById(R.id.confirmation_request).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new HttpRequestSendSMS().execute(mCurrentOrder.getmBuyerCellNumber());
            }
        });

        findViewById(R.id.order_confirmation_check_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView smsInput = (TextView)findViewById(R.id.order_confirmation_number_input);
                if (requiredSMSNum.equals(smsInput.getText().toString())) {
                    Toast.makeText(BrownSMSActivity.this, "Correct confirmation num", Toast.LENGTH_SHORT).show();
                    new HttpRequestOrderComplete().execute();
                } else {
                    Toast.makeText(BrownSMSActivity.this, "Wrong SMS confirmation num", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private class HttpRequestSendSMS extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... phoneNums) {
            try {
                String phoneNum = phoneNums[0];
//                final String url = "http://10.0.2.2:8080/BrownTime/json/requestSMS";
                final String url = "http://browntime123.cafe24.com/json/requestSMS";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.setMessageConverters(new JSONRequest().getMessageConverters());
//                restTemplate.postForObject(url, String.class);

                BrownBuyer buyer = new BrownBuyer();
                buyer.setmBuyerName(mCurrentOrder.getmBuyerName());
                buyer.setmBuyerCellNumber(mCurrentOrder.getmBuyerCellNumber());
                requiredSMSNum = getRandomFourDigits();
                buyer.setmSMSNumber(Integer.parseInt(requiredSMSNum));
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

    private String getRandomFourDigits() {
        List<Integer> numbers = new ArrayList<Integer>();
        for(int i = 0; i < 10; i++){
            numbers.add(i);
        }

        Collections.shuffle(numbers);

        String result = "";
        for(int i = 0; i < 4; i++){
            result += numbers.get(i).toString();
        }

        return result;
    }
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
            Intent i = new Intent(BrownSMSActivity.this, BrownOrderActivity.class);
//            OrderLab.get(BrownSMSActivity.this).addOrder(mCurrentOrder);
            startActivity(i);
        }
    }
}
