package com.android.browntime;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public class BrownActivity extends FragmentActivity {

	protected Fragment createFragment() {
		return new BrownMenuFragment();
	}

//	private BrownMenu[] mMenuBank = new BrownMenu[] {
//			new BrownMenu(R.string.americano, 5500),
//			new BrownMenu(R.string.caffe_latte, 5000),
//			new BrownMenu(R.string.korean_roll, 3000),
//		};
//	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_brown);

		FragmentManager fm = getSupportFragmentManager();
		Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);
//		
		if (fragment == null) {
			fragment = new BrownMenuFragment();
			fm.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
		}
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//
//		// Inflate the menu; this adds items to the action bar if it is present.
//		MenuInflater inflater = getMenuInflater();
//		inflater.inflate(R.menu.brown, menu);
//		return true;
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
////		int id = item.getItemId();
////		if (id == R.id.action_settings) {
////			return true;
////		}
//		return super.onOptionsItemSelected(item);
//	}
//
//	/**
//	 * A placeholder fragment containing a simple view.
//	 */
//	public static class PlaceholderFragment extends Fragment {
//
//		public PlaceholderFragment() {
//		}
//
//		@Override
//		public View onCreateView(LayoutInflater inflater, ViewGroup container,
//				Bundle savedInstanceState) {
//			View rootView = inflater.inflate(R.layout.fragment_activity,
//					container, false);
//			return rootView;
//		}
//	}

}
