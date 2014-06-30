package com.android.browntime;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

public class BrownListFragment extends ListFragment {

	private static final String TAG = "CrimeListFragment";	
	private ArrayList<BrownMenu> mMenus;
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		getActivity().setTitle(R.string.me);
		mMenus = MenuLab.get(getActivity()).getMenus();
		
//		ArrayAdapter<BrownMenu> adapter = new ArrayAdapter<BrownMenu>(getActivity(), android.R.layout.simple_list_item_1, mMenus);
		
		BrownMenuAdapter adapter = new BrownMenuAdapter(mMenus);
		setListAdapter(adapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {		
		BrownMenu c = ((BrownMenuAdapter)getListAdapter()).getItem(position);
		
//		Intent i = new Intent(getActivity(), CrimeActivity.class);
		Intent i = new Intent(getActivity(), BrownPagerActivity.class);
		i.putExtra(BrownMenuFragment.EXTRA_MENU_ID, c.getId());
		startActivity(i);
		Log.d(TAG,  "was clicked");
	}
	
	private class BrownMenuAdapter extends ArrayAdapter<BrownMenu> {
		public BrownMenuAdapter(ArrayList<BrownMenu> menus) {
			super(getActivity(), 0, menus);
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = getActivity().getLayoutInflater()
						.inflate(R.layout.list_item_menu, null);
			}
			
			BrownMenu c = getItem(position);
			
			TextView nameTextView = (TextView)convertView.findViewById(R.id.menu_list_item_nameTextView);
			nameTextView.setText(c.getName());
			TextView priceTextView = (TextView)convertView.findViewById(R.id.menu_list_item_priceTextView);
			priceTextView.setText(String.valueOf(c.getPrice()));
//			CheckBox solvedCheckBox = (CheckBox)convertView.findViewById(R.id.menu_list_item_CheckBox);
//			solvedCheckBox.setChecked(c.get);
			
			return convertView;
		}
	}
	
	@Override
	public void onResume() {
		super.onResume();
		((BrownMenuAdapter)getListAdapter()).notifyDataSetChanged();
	}
	
}
