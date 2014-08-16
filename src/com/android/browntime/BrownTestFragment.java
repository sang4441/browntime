package com.android.browntime;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.browntime.dataLab.OrderLab;
import com.android.browntime.model.BrownOrder;

import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by kimsanghwan on 8/2/2014.
 */
public class BrownTestFragment extends Fragment {

    private ArrayList<BrownOrder> orders;
    View v;
    ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_user_history, parent, false);
        listView = (ListView) v.findViewById(R.id.content_frame);

        new HttpRequestTask().execute();

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
//        MenuItem item = menu.findItem(R.id.user_order_cart);
//        item.setVisible(false);
        inflater.inflate(R.menu.fragment_user_orders, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.user_order_refresh:
                new HttpRequestTask().execute();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class BrownOrderAdapter extends ArrayAdapter<BrownOrder> {

        public BrownOrderAdapter(List<BrownOrder> orders) {
            super(getActivity(), 0, orders);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_user_order, parent, false);
            }

//            View orderWrap = convertView.findViewById(R.id.order_list_wrap);
//            if (order)
//            orderWrap.setBackgroundColor();

            BrownOrder order = getItem(position);
            String packageName = getActivity().getPackageName();

//            TextView orderBuyerNameView = (TextView)convertView.findViewById(R.id.order_id);
//            orderBuyerNameView.setText("Order #:" + String.valueOf(order.getmId()));

            TextView orderTypeView = (TextView)convertView.findViewById(R.id.order_type);
            String typeName = getResources().getString(getResources().getIdentifier(order.getmTypeName(),"string", packageName));
            orderTypeView.setText(typeName);

            TextView orderStatusView = (TextView)convertView.findViewById(R.id.order_status);
            String orderName = getResources().getString(getResources().getIdentifier(order.getmStatusName(),"string", packageName));
            orderStatusView.setText(orderName);

            TextView orderDate = (TextView)convertView.findViewById(R.id.order_date);
            Date orderTime = order.getmTime();
            Calendar calendar = GregorianCalendar.getInstance();
            calendar.setTime(orderTime);
            String hour = String.valueOf(calendar.get(Calendar.HOUR));
            String minute = String.valueOf(calendar.get(Calendar.MINUTE));


            if (order.getmStatusId() == 1) {
                orderDate.setText(getString(R.string.order_time_without_duration, hour, minute));
                orderStatusView.setTextColor(Color.parseColor("#FF8000"));
            } else if (order.getmStatusId() == 2) {
                orderDate.setText(getString(R.string.order_time_with_duration, hour, minute, String.valueOf(order.getmDuration())));
                orderStatusView.setTextColor(Color.parseColor("#31B404"));
            } else {
                orderStatusView.setTextColor(Color.parseColor("#6E6E6E"));
            }


//            TextView orderDate = (TextView)convertView.findViewById(R.id.order_date);
//            orderDate.setText(order.getmTime().toString());
//
//            TextView orderBuyerNumber = (TextView)convertView.findViewById(R.id.order_buyer_number);
//            orderBuyerNumber.setText(String.valueOf(order.getmBuyerCellNumber()));

            return convertView;
        }
    }


    private class HttpRequestTask extends AsyncTask<Void, Void, List<BrownOrder>> {
        @Override
        protected List<BrownOrder> doInBackground(Void... params) {
            try {

//                final String url = "http://browntime123.cafe24.com/json/getOrder";
//                final String url = "http://10.0.2.2:8080/BrownTime/json/getOrderByPhone/1099155894";
                final String url = "http://browntime123.cafe24.com/json/getOrderByPhone/01099155894";

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

            OrderLab.get(getActivity()).setOrders(orders);
            BrownOrderAdapter adapter = new BrownOrderAdapter(orders);

            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    BrownOrder order = (BrownOrder)(parent.getAdapter().getItem(position));
                    Intent i = new Intent(getActivity(), BrownUserOrderPagerActivity.class);
                    i.putExtra(BrownUserOrderFragment.EXTRA_ORDER_ID, order.getmId());
                    startActivity(i);
                }
            });
        }
    }

//    @Override
//    public void onListItemClick(ListView l, View v, int position, long id) {
//        BrownOrder order = (BrownOrder)(getListAdapter()).getItem(position);
//        Intent i = new Intent(getActivity(), BrownUserOrderPagerActivity.class);
//        i.putExtra(BrownUserOrderFragment.EXTRA_ORDER_ID, order.getmId());
//        startActivity(i);
//    }
}
