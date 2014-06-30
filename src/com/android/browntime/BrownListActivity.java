package com.android.browntime;

import android.support.v4.app.Fragment;

public class BrownListActivity extends SingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		return new BrownListFragment();
	}

}
