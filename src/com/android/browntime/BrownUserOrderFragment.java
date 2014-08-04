package com.android.browntime;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.browntime.dataLab.OrderLab;
import com.android.browntime.model.BrownCart;
import com.android.browntime.model.BrownOrder;

import java.util.List;


/**
 * Created by kimsanghwan on 7/25/2014.
 */
public class BrownUserOrderFragment extends Fragment {
    public static final String EXTRA_ORDER_ID = "browntime.order_id";

    private BrownOrder mOrder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mOrder = new BrownOrder();
        int orderId = (int)getArguments().getInt(EXTRA_ORDER_ID);
        mOrder = OrderLab.get(getActivity()).getOrder(orderId);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_order, parent, false);

        TextView orderIdView = (TextView)v.findViewById(R.id.order_id);
        orderIdView.setText(String.valueOf(mOrder.getmId()));

        ListView listView = (ListView)v.findViewById(R.id.fragment_carts);
        BrownCartAdapter adapter = new BrownCartAdapter(mOrder.getmCarts());
        listView.setAdapter(adapter);

        return v;
    }

    private class BrownCartAdapter extends ArrayAdapter<BrownCart> {

        public BrownCartAdapter(List<BrownCart> carts) {
            super(getActivity(), 0, carts);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_user_cart, parent, false);
            }

            BrownCart cart = getItem(position);

            TextView cartQuantity = (TextView) convertView.findViewById(R.id.order_cart_quantity);
            cartQuantity.setText(String.valueOf(cart.getmQuantity()));
            TextView cartName = (TextView) convertView.findViewById(R.id.order_cart_name);
            cartName.setText(cart.getmName());
            TextView cartPrice = (TextView) convertView.findViewById(R.id.order_cart_price);
            cartPrice.setText(String.valueOf(cart.getmPrice()));

            return convertView;
        }
    }


    public static BrownUserOrderFragment newInstance(int orderId) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_ORDER_ID, orderId);

        BrownUserOrderFragment fragment = new BrownUserOrderFragment();
        fragment.setArguments(args);

        return fragment;
    }
}