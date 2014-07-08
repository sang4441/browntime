package com.android.browntime;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class BrownMenuListFragment extends ListFragment {

	public static final String MENU_TYPE = "object";
	private static final String TAG = "CrimeListFragment";	
	private ArrayList<BrownMenu> mMenus;
	
	private EditText mMenuQuantity;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		getActivity().setTitle(R.string.me);
//		if (savedInstanceState == null) {
//			mMenus = MenuLab.get(getActivity()).getMenus(1);
//		} else {
//			mMenus = MenuLab.get(getActivity()).getMenus(2);
//		}
		
//		ArrayAdapter<BrownMenu> adapter = new ArrayAdapter<BrownMenu>(getActivity(), android.R.layout.simple_list_item_1, mMenus);
		
		LayoutInflater gridInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = gridInflater.inflate(R.layout.main, null);
//
	    GridView gridview = (GridView)v.findViewById(R.id.gridview);
	    
		Bundle args = getArguments();
		mMenus = MenuLab.get(getActivity()).getMenus(args.getInt(MENU_TYPE));
		
		BrownMenuAdapter adapter = new BrownMenuAdapter(mMenus);
		
		
//		gridview.setAdapter(adapter);
		setListAdapter(adapter);
	}
	
	public void listFetch(int type) {
		ArrayList<BrownMenu> mMenuTmp = MenuLab.get(getActivity()).getMenus(type);;
    	BrownMenuAdapter adapter = new BrownMenuAdapter(mMenuTmp);
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
		private Context mContext;
		 
		public BrownMenuAdapter(ArrayList<BrownMenu> menus) {
			super(getActivity(), 0, menus);
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v;
			
			if (convertView == null) {
//				v = getActivity().getLayoutInflater().inflate(R.layout.main, null);
//			    v.setLayoutParams(new GridView.LayoutParams(200,200));
				convertView = getActivity().getLayoutInflater()
						.inflate(R.layout.list_item_menu, parent, false);
			} else {
				v = convertView;
			}
			
//			GridView gridview = (GridView)v.findViewById(R.id.gridview);
//			
//			return gridview;
			
			BrownMenu c = getItem(position);
			
			ImageView imageView = (ImageView) convertView.findViewById(R.id.menu_list_item_imageView);
			imageView.setImageResource(R.drawable.americano);
			TextView nameTextView = (TextView)convertView.findViewById(R.id.menu_list_item_nameTextView);
			nameTextView.setText(c.getName());
			TextView priceTextView = (TextView)convertView.findViewById(R.id.menu_list_item_priceTextView);
			priceTextView.setText(String.valueOf(c.getPrice()));
			
			return convertView;
		}
	}
	
	@Override
	public void onResume() {
		super.onResume();
//		((BrownMenuAdapter)getAdapter).notifyDataSetChanged();
	}
	
}
