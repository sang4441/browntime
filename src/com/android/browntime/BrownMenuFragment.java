package com.android.browntime;

import java.util.ArrayList;
import java.util.UUID;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BrownMenuFragment extends Fragment {

	public static final String EXTRA_MENU_ID = "com.android.browntime.menu_id";

	private ArrayList<BrownMenu> mMenus;
	
	private BrownMenu mMenu;
	private TextView mMenuName;
	private TextView mMenuPrice;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
//		mMenu = new BrownMenu(1, 1);
		mMenu = new BrownMenu();
		UUID menuId = (UUID)getArguments().getSerializable(EXTRA_MENU_ID);
		mMenu = MenuLab.get(getActivity()).getMenu(menuId);	
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_menu,  parent, false);
	
		mMenuName = (TextView)v.findViewById(R.id.menu_name);
		mMenuName.setText(mMenu.getName());
		
		mMenuPrice = (TextView)v.findViewById(R.id.menu_price);
		mMenuPrice.setText(String.valueOf(mMenu.getPrice()));	
		
		return v;
	}
	
	public static BrownMenuFragment newInstance(UUID menuId) {
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_MENU_ID, menuId);
		
		BrownMenuFragment fragment = new BrownMenuFragment();
		fragment.setArguments(args);
		
		return fragment;		
	}
	
//	@Override
//	public void onActivityResult(int requestCode, int resultCode, Intent data) {
//		if (resultCode != Activity.RESULT_OK) 
//			return;
//		if (requestCode == REQUEST_DATE) {
//			Date date = (Date)data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
//			mCrime.setDate(date);
//			updateDate();
//		}		
//	}
}
