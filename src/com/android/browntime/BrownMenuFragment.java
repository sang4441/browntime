package com.android.browntime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.UUID;

public class BrownMenuFragment extends Fragment {

	public static final String EXTRA_MENU_ID = "com.android.browntime.menu_id";

	private ArrayList<BrownMenu> mMenus;
	
	private BrownMenu mMenu;
	private TextView mMenuName, mMenuPrice, mMenuQuantity, mMenuPriceTotal, mMenuDescription;
	private ImageView mMenuImage, mmMenuQuantityPlus, mmMenuQuantityMinus;
	private Button mMenuAddToCart, mMenuCheckout;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mMenu = new BrownMenu();
		UUID menuId = (UUID)getArguments().getSerializable(EXTRA_MENU_ID);
		mMenu = MenuLab.get(getActivity()).getMenu(menuId);	

	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_menu,  parent, false);
	
		mMenuPriceTotal = (TextView)v.findViewById(R.id.menu_price_total);
		mMenuPriceTotal.setText(String.valueOf(mMenu.getmPrice()));
		
		mMenuName = (TextView)v.findViewById(R.id.menu_name);
		mMenuName.setText(mMenu.getmName());
		
		mMenuPrice = (TextView)v.findViewById(R.id.menu_price);
		mMenuPrice.setText(String.valueOf(mMenu.getmPrice()));
		
		mMenuImage = (ImageView)v.findViewById(R.id.menu_image);
		mMenuImage.setImageResource(R.drawable.americano);
		
		mMenuDescription = (TextView)v.findViewById(R.id.menu_description);
		mMenuDescription.setText(mMenu.getmDescription());
		
		mMenuQuantity = (TextView)v.findViewById(R.id.menu_quantity);
		mMenuQuantity.addTextChangedListener(new TextWatcher() {
			public void onTextChanged(CharSequence c, int start, int before, int count) {
//				mMenu.setQuantity(Integer.parseInt(c.toString()));
				mMenuPriceTotal.setText(String.valueOf(mMenu.getmPrice()*Integer.parseInt(c.toString())));
			}
			
			public void beforeTextChanged(CharSequence c, int start, int count, int after) {
				
			}
			public void afterTextChanged(Editable c) {
				
			}
		});
		
		
		mmMenuQuantityPlus = (ImageView)v.findViewById(R.id.menu_quantity_plus);
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
		
		mmMenuQuantityMinus = (ImageView)v.findViewById(R.id.menu_quantity_minus);
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
		
		mMenuAddToCart = (Button)v.findViewById(R.id.menu_add_to_cart);
		mMenuAddToCart.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				saveMenuToCart();
				Intent i = new Intent(getActivity(), CollectionDemoActivity.class);
	    		startActivity(i);
			}	
		});		
		
		mMenuCheckout = (Button)v.findViewById(R.id.menu_checkout);
		mMenuCheckout.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {	
				saveMenuToCart();
				Intent i = new Intent(getActivity(), BrownCartListActivity.class);
	    		startActivity(i);
			}	
		});		

		return v;
	}
	
	public void saveMenuToCart() {
		BrownCart newItem = new BrownCart(mMenu.getmName(), mMenu.getmPrice(), mMenu.getmCategory());
//		newItem.setQuantity(Integer.parseInt(mMenuQuantity.getText().toString()));
		CartLab.get(getActivity()).addMenu(newItem);
	}
	public static BrownMenuFragment newInstance(UUID menuId) {
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_MENU_ID, menuId);
		
		BrownMenuFragment fragment = new BrownMenuFragment();
		fragment.setArguments(args);
		
		return fragment;		
	}

}
