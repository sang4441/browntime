package com.android.browntime;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

public class BrownMenuListActivity extends SingleFragmentActivity {

	public static final String EXTRA_MENU_TYPE = "com.android.browntime.menu_type";
	private TextView mView1, mView2, mView3, mView4;
	
	@Override
	protected Fragment createFragment() {
		return new BrownMenuListFragment();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_brown);
		
		mView1 = (TextView)findViewById(R.id.menu_coffee);
		mView1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Bundle args = new Bundle();
				args.putSerializable(EXTRA_MENU_TYPE, 1);
				finish();
				new BrownMenuListFragment().onCreate(args);
//				Intent i = new Intent()
////				Intent i = new Intent(getActivity(), CrimeActivity.class);
//				Intent i = new Intent(getActivity(), BrownPagerActivity.class);
//				i.putExtra(BrownMenuFragment.EXTRA_MENU_ID, c.getId());
//				startActivity(i);
			}
		});
		
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
