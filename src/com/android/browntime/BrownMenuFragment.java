package com.android.browntime;

import java.util.ArrayList;
import java.util.UUID;

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
import android.widget.Toast;

public class BrownMenuFragment extends Fragment {

	public static final String EXTRA_MENU_ID = "com.android.browntime.menu_id";

	private ArrayList<BrownMenu> mMenus;
	
	private BrownMenu mMenu;
	private TextView mMenuName, mMenuPrice, menuQuantity, menuPriceTotal;
	private ImageView mMenuImage, mMenuQuantityPlus, mMenuQuantityMinus;
	private Button mMenuAddToCart;
	
	
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
	
		menuPriceTotal = (TextView)v.findViewById(R.id.menu_price_total);
		menuPriceTotal.setText(String.valueOf(mMenu.getPrice()));
		
		mMenuName = (TextView)v.findViewById(R.id.menu_name);
		mMenuName.setText(mMenu.getName());
		
		mMenuPrice = (TextView)v.findViewById(R.id.menu_price);
		mMenuPrice.setText(String.valueOf(mMenu.getPrice()));	
		
		mMenuImage = (ImageView)v.findViewById(R.id.menu_image);
		mMenuImage.setImageResource(R.drawable.americano);
		
		menuQuantity = (TextView)v.findViewById(R.id.menu_quantity);
		menuQuantity.addTextChangedListener(new TextWatcher() {
			public void onTextChanged(CharSequence c, int start, int before, int count) {
				mMenu.setQuantity(Integer.parseInt(c.toString()));
				menuPriceTotal.setText(String.valueOf(mMenu.getPrice()*mMenu.getQuantity()));
			}
			
			public void beforeTextChanged(CharSequence c, int start, int count, int after) {
				
			}
			public void afterTextChanged(Editable c) {
				
			}
		});
		
		
		mMenuQuantityPlus = (ImageView)v.findViewById(R.id.menu_quantity_plus);
		mMenuQuantityPlus.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String quantity;
				if (menuQuantity.getText().equals(null)) {
					quantity = "1";
				} else {
					quantity = menuQuantity.getText().toString();
				}
				menuQuantity.setText(String.valueOf((Integer.parseInt(quantity)+1)));
				

			}
			
			
		});
		
		mMenuQuantityMinus = (ImageView)v.findViewById(R.id.menu_quantity_minus);
		mMenuQuantityMinus.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int quantity;
				if (menuQuantity.getText().equals(null)) {
					quantity = 1;
				} else {
					quantity = Integer.parseInt(menuQuantity.getText().toString());					
				}
				if (quantity < 1) {
					
				} else {
					menuQuantity.setText(String.valueOf((quantity)-1));
				}

			}
			
		});
		
		mMenuAddToCart = (Button)v.findViewById(R.id.menu_add_to_cart);
		mMenuAddToCart.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				BrownCart newItem = new BrownCart(mMenu.getName(), mMenu.getPrice(), mMenu.getType());
				newItem.setQuantity(Integer.parseInt(menuQuantity.getText().toString()));
				CartLab.get(getActivity()).addMenu(newItem);
				
//				menuPriceTotal.setText(String.valueOf(CartLab.get(getActivity()).getPriceTotal()));
				
				Toast.makeText(getActivity(), "total price= "+CartLab.get(getActivity()).getPriceTotal(), Toast.LENGTH_SHORT).show();
			}	
		});		
		
		
		return v;
	}
	
	public static BrownMenuFragment newInstance(UUID menuId) {
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_MENU_ID, menuId);
		
		BrownMenuFragment fragment = new BrownMenuFragment();
		fragment.setArguments(args);
		
		return fragment;		
	}

}
