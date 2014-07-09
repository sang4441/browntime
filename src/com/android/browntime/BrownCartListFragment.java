package com.android.browntime;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class BrownCartListFragment extends ListFragment {
	
	private ArrayList<BrownCart> mCartItems;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mCartItems = CartLab.get(getActivity()).getMenus();
		
		BrownCartAdapter adapter = new BrownCartAdapter(mCartItems);
		
		
		setListAdapter(adapter);
	}
	
	private class BrownCartAdapter extends ArrayAdapter<BrownCart> {
		 
		public BrownCartAdapter(ArrayList<BrownCart> menus) {
			super(getActivity(), 0, menus);
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			if (convertView == null) {
				convertView = getActivity().getLayoutInflater()
						.inflate(R.layout.list_item_cart, parent, false);
			} 
			
			BrownCart c = getItem(position);
			
			
			TextView nameTextView = (TextView)convertView.findViewById(R.id.cart_menu_name);
			nameTextView.setText(c.getName());
			
			TextView priceTextView = (TextView)convertView.findViewById(R.id.cart_menu_price);
			priceTextView.setText(String.valueOf(c.getPrice()));
			
			TextView quantityTextView = (TextView)convertView.findViewById(R.id.cart_menu_quantity);
			quantityTextView.setText(String.valueOf(c.getQuantity()));
			
			TextView priceTotalTextView = (TextView)convertView.findViewById(R.id.cart_menu_price_total);
			priceTotalTextView.setText(String.valueOf(c.getPrice()));
			
			return convertView;
		}
	}
}
