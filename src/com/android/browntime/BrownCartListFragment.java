package com.android.browntime;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class BrownCartListFragment extends ListFragment {
	
	private ArrayList<BrownCart> mCartItems;
	private BrownCart cart;

	
	
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
			
			cart = getItem(position);
			
			TextView nameTextView = (TextView)convertView.findViewById(R.id.cart_menu_name);
			nameTextView.setText(cart.getmName());
			
			TextView priceTextView = (TextView)convertView.findViewById(R.id.cart_menu_price);
			priceTextView.setText(String.valueOf(cart.getmPrice()));
			
//			TextView quantityTextView = (TextView)convertView.findViewById(R.id.cart_menu_quantity);
//			quantityTextView.setText(String.valueOf(cart.getQuantity()));
			
			final TextView priceTotalTextView = (TextView)convertView.findViewById(R.id.cart_menu_price_total);
			priceTotalTextView.setText(String.valueOf(cart.getmQuantity()*cart.getmPrice()));
			
			final TextView mMenuQuantity = (TextView)convertView.findViewById(R.id.cart_menu_quantity);
//			mMenuQuantity = (TextView)convertView.findViewById(R.id.cart_menu_quantity);
			mMenuQuantity.addTextChangedListener(new TextWatcher() {
				public void onTextChanged(CharSequence c, int start, int before, int count) {
					cart.setmQuantity(Integer.parseInt(c.toString()));
					priceTotalTextView.setText(String.valueOf(cart.getmPrice()*cart.getmQuantity()));
					((BrownCartListActivity)getActivity()).refreshSum();
				}
				
				public void beforeTextChanged(CharSequence c, int start, int count, int after) {
					
				}
				public void afterTextChanged(Editable c) {
					
				}
			});
			
			
			ImageButton mmMenuQuantityPlus = (ImageButton)convertView.findViewById(R.id.cart_menu_quantity_plus);
			mmMenuQuantityPlus.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					String quantity;
					
					if (mMenuQuantity.getText().equals(null)) {
						quantity = "1";
					} else {
						quantity = mMenuQuantity.getText().toString();
					}
					mMenuQuantity.setText(String.valueOf((Integer.parseInt(quantity)+1)));
				}		
			});
			
			ImageButton mmMenuQuantityMinus = (ImageButton)convertView.findViewById(R.id.cart_menu_quantity_minus);
			mmMenuQuantityMinus.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					int quantity;
					if (mMenuQuantity.getText().equals(null)) {
						quantity = 1;
					} else {
						quantity = Integer.parseInt(mMenuQuantity.getText().toString());					
					}
					if (quantity < 2) {
						
					} else {
						mMenuQuantity.setText(String.valueOf((quantity)-1));
					}

				}
				
			});
			
			Button deleteButton = (Button)convertView.findViewById(R.id.cart_menu_button_delete);
			deleteButton.setTag(position);
			deleteButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					CartLab.get(getActivity()).deleteCart(cart.getmId());
					getActivity().finish();
					startActivity(getActivity().getIntent());
//					BrownCartListFragment.refresh();
				}
			});
			
			return convertView;
		}
	}
}
