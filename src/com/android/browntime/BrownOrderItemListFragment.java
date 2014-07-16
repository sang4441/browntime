package com.android.browntime;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class BrownOrderItemListFragment extends ListFragment {
	private ArrayList<BrownCart> mCartItems;
	
	BrownOrder currentOrder;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		
		currentOrder = OrderLab.get(getActivity()).getLastOrder();
		
		mCartItems = currentOrder.getCarts();
		BrownOrderAdapter adapter = new BrownOrderAdapter(mCartItems);
		
		
		setListAdapter(adapter);
	}
	
	private class BrownOrderAdapter extends ArrayAdapter<BrownCart> {
		 
		public BrownOrderAdapter(ArrayList<BrownCart> menus) {
			super(getActivity(), 0, menus);
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			if (convertView == null) {
				convertView = getActivity().getLayoutInflater()
						.inflate(R.layout.list_item_order, parent, false);
			} 
			
			BrownCart c = getItem(position);			
			
			TextView nameTextView = (TextView)convertView.findViewById(R.id.order_menu_name);
			nameTextView.setText(c.getName());
			
			TextView priceTextView = (TextView)convertView.findViewById(R.id.order_menu_price);
			priceTextView.setText(String.valueOf(c.getPrice()));
			
			TextView quantityTextView = (TextView)convertView.findViewById(R.id.order_menu_quantity);
			quantityTextView.setText(String.valueOf(c.getQuantity()));
			
			TextView priceTotalTextView = (TextView)convertView.findViewById(R.id.order_menu_price_total);
			priceTotalTextView.setText(String.valueOf(c.getTotalPrice()));
			
			return convertView;
		}	
	}
}
