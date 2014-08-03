package com.android.browntime;

import android.app.ListFragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.browntime.dataLab.OrderLab;
import com.android.browntime.model.BrownOrder;

import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by kimsanghwan on 7/24/2014.
 */
public class BrownUserOrderListFragment extends ListFragment {

    private ArrayList<BrownOrder> orders;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new HttpRequestTask().execute();
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

            BrownOrder order = getItem(position);
            String packageName = getActivity().getPackageName();

            TextView orderBuyerNameView = (TextView)convertView.findViewById(R.id.order_id);
            orderBuyerNameView.setText(order.getmBuyerName());

            TextView orderTypeView = (TextView)convertView.findViewById(R.id.order_type);
            String typeName = getResources().getString(getResources().getIdentifier(order.getmTypeName(),"string", packageName));
            orderTypeView.setText(typeName);

            TextView orderStatusView = (TextView)convertView.findViewById(R.id.order_status);
            String orderName = getResources().getString(getResources().getIdentifier(order.getmStatusName(),"string", packageName));
            orderStatusView.setText(orderName);

            TextView orderDate = (TextView)convertView.findViewById(R.id.order_date);
            orderDate.setText(order.getmTime().toString());
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
                final String url = "http://browntime123.cafe24.com/json/getOrderByPhone/1099155894";

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

            setListAdapter(adapter);
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        BrownOrder order = (BrownOrder)(getListAdapter()).getItem(position);
        Intent i = new Intent(getActivity(), BrownUserOrderPagerActivity.class);
        i.putExtra(BrownUserOrderFragment.EXTRA_ORDER_ID, order.getmId());
        startActivity(i);
    }
}
