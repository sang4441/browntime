package com.android.browntime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.browntime.model.BrownMenu;

import java.util.ArrayList;

public class BrownMenuListFragment extends Fragment {

	public static final String MENU_TYPE = "object";
	private static final String TAG = "CrimeListFragment";	
	private ArrayList<BrownMenu> mMenus;
	
	private EditText mMenuQuantity;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle args = getArguments();
		mMenus = MenuLab.get(getActivity()).getMenus(args.getInt(MENU_TYPE));
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.main,  parent, false);
		GridView gridview = (GridView)v.findViewById(R.id.gridview);
		BrownMenuAdapter adapter = new BrownMenuAdapter(mMenus);
	    gridview.setAdapter(adapter);
	    
	    gridview.setOnItemClickListener(new OnItemClickListener() {
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	    		BrownMenu c = (BrownMenu)parent.getAdapter().getItem(position);	
	    		Intent i = new Intent(getActivity(), BrownPagerActivity.class);
	    		i.putExtra(BrownMenuFragment.EXTRA_MENU_ID, c.getmId());
	    		startActivity(i);
	    		Log.d(TAG,  "was clicked");
	        }
	    });
	    
		return v;
	}
	
	private class BrownMenuAdapter extends ArrayAdapter<BrownMenu> {		 
		public BrownMenuAdapter(ArrayList<BrownMenu> menus) {
			super(getActivity(), 0, menus);
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			if (convertView == null) {
				convertView = getActivity().getLayoutInflater()
						.inflate(R.layout.list_item_menu, parent, false);
			}			
			
			BrownMenu c = getItem(position);
			
			ImageView imageView = (ImageView) convertView.findViewById(R.id.menu_list_item_imageView);
			imageView.setImageResource(R.drawable.americano);
			TextView nameTextView = (TextView)convertView.findViewById(R.id.menu_list_item_nameTextView);
			nameTextView.setText(c.getmName());
			TextView priceTextView = (TextView)convertView.findViewById(R.id.menu_list_item_priceTextView);
			priceTextView.setText(String.valueOf(c.getmPrice()));
			
			return convertView;
		}
		
	}
	
}
