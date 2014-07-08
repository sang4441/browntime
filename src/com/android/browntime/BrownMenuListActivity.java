package com.android.browntime;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class BrownMenuListActivity extends SingleFragmentActivity {

	public static final String EXTRA_MENU_TYPE = "com.android.browntime.menu_type";
	private TextView mView1, mView2, mView3, mView4;
	GridView gridView;

	
	@Override
	protected Fragment createFragment() {
		return new BrownMenuListFragment();
	}
	
	 
//	static final String[] numbers = new String[] { 
//			"A", "B", "C", "D", "E",
//			"F", "G", "H", "I", "J",
//			"K", "L", "M", "N", "O",
//			"P", "Q", "R", "S", "T",
//			"U", "V", "W", "X", "Y", "Z"};
// 
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
// 
//		setContentView(R.layout.main);
// 
//		gridView = (GridView) findViewById(R.id.gridview);
// 
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//				android.R.layout.simple_list_item_1, numbers);
// 
//		gridView.setAdapter(adapter);
//	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_brown);
		
		mView1 = (TextView)findViewById(R.id.menu_cart);
		mView1.setText("Go to cart");
//		mView1.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				Bundle args = new Bundle();
//				args.putSerializable(EXTRA_MENU_TYPE, 1);
//				finish();
//				new BrownMenuListFragment().onCreate(args);
////				Intent i = new Intent()
//////				Intent i = new Intent(getActivity(), CrimeActivity.class);
////				Intent i = new Intent(getActivity(), BrownPagerActivity.class);
////				i.putExtra(BrownMenuFragment.EXTRA_MENU_ID, c.getId());
////				startActivity(i);
//			}
//		});
		
//		mView2.findViewById(R.id.menu_non_coffee);
//		mView2.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				new BrownListFragment().listFetch(2);
//			}
//		});
//		
//		
//		mView3.findViewById(R.id.menu_side);
		
	}
}
