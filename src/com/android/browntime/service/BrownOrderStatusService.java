package com.android.browntime.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.android.browntime.JSONRequest;
import com.android.browntime.model.BrownOrder;

import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by kimsanghwan on 8/5/2014.
 */
public class BrownOrderStatusService extends IntentService {

    public static final String NOTIFICATION = "com.browntime.android.service.receiver";

    public BrownOrderStatusService() {
        super("BrownOrderStatusService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        new HttpRequestGetOrder().execute();
    }

    private class HttpRequestGetOrder extends AsyncTask<Void, Void, List<BrownOrder>> {
        @Override
        protected List<BrownOrder> doInBackground(Void... params) {
            try {

                final String url = "http://10.0.2.2:8080/BrownTime/json/getOrder";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.setMessageConverters(new JSONRequest().getMessageConverters());

                BrownOrder[] orders = restTemplate.getForObject(url, BrownOrder[].class);

                return Arrays.asList(orders);
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<BrownOrder> orders) {
            Intent intent = new Intent(NOTIFICATION);
            //low priority
//            intent.putExtra(RESULT, orders);
//
//            int updatedOrderSize = 0;
//            if (OrderLab.get(getApplicationContext()).getOrders().size() < orders.size()) {
//                OrderLab.get(getApplicationContext()).clearOrders();
//                OrderLab.get(getApplicationContext()).setOrders(orders);
//                updatedOrderSize = orders.size() - OrderLab.get(getApplicationContext()).getOrders().size();
//            }
//            intent.putExtra(BrownTimeAdminOrderActivity.SERVICE_KEY, updatedOrderSize);
//            sendBroadcast(intent);
        }
    }
}