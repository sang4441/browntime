package com.android.browntime;

import android.content.Intent;
import android.graphics.LightingColorFilter;
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

import com.android.browntime.activity.BrownCartListActivity;
import com.android.browntime.activity.DrawerActivity;
import com.android.browntime.dataLab.CartLab;
import com.android.browntime.dataLab.MenuLab;
import com.android.browntime.model.BrownCart;
import com.android.browntime.model.BrownMenu;

import java.util.ArrayList;

public class BrownMenuFragment extends Fragment {

	public static final String EXTRA_MENU_ID = "com.android.browntime.menu_id";

	private ArrayList<BrownMenu> mMenus;
	
	private BrownMenu mMenu;
	private TextView mMenuName, mMenuPrice, mMenuQuantity, mMenuPriceTotal, mMenuDescription, mInstruction;
	private ImageView mMenuImage, mmMenuQuantityPlus, mmMenuQuantityMinus;
	private Button mMenuAddToCart, mMenuCheckout;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mMenu = new BrownMenu();
		int menuId = (int)getArguments().getInt(EXTRA_MENU_ID);
		mMenu = MenuLab.get(getActivity()).getMenu(menuId);

	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_menu,  parent, false);
        String packageName = getActivity().getPackageName();

        mMenuPriceTotal = (TextView)v.findViewById(R.id.menu_price_total);
		mMenuPriceTotal.setText(String.valueOf(mMenu.getmPrice()));

		mMenuName = (TextView)v.findViewById(R.id.menu_name);
        String menuName = getResources().getString(getResources().getIdentifier(mMenu.getmName(),"string", packageName));
		mMenuName.setText(menuName);
		
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
				Intent i = new Intent(getActivity(), DrawerActivity.class);
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
        mMenuCheckout.getBackground().setColorFilter(new LightingColorFilter(0xFFFFFFFF, 0xFFAA0000));

        mInstruction = (TextView)v.findViewById(R.id.menu_special_instruction);
		return v;
	}
	
	public void saveMenuToCart() {
		BrownCart newCart = new BrownCart(mMenu.getmId(), mMenu.getmName(), mMenu.getmPrice(), mMenu.getmCategory(), mMenu.getmDescription());
        int cartItemQuantity = Integer.parseInt(mMenuQuantity.getText().toString());
        newCart.setmQuantity(cartItemQuantity);
        newCart.setmInstruction(mInstruction.getText().toString());
        newCart.setmPriceTotal(cartItemQuantity*newCart.getmPrice());
		CartLab.get(getActivity()).addCart(newCart);
	}
	public static BrownMenuFragment newInstance(int menuId) {
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_MENU_ID, menuId);
		
		BrownMenuFragment fragment = new BrownMenuFragment();
		fragment.setArguments(args);
		
		return fragment;		
	}

}
