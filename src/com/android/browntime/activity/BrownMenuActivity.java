package com.android.browntime.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;

import com.android.browntime.fragment.BrownMenuFragment;
import com.android.browntime.R;

public class BrownMenuActivity extends ActionBarActivity {

	protected Fragment createFragment() {
		return new BrownMenuFragment();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_brown);
	
        
        FragmentManager fm = getSupportFragmentManager();
		Fragment fragment = fm.findFragmentById(R.id.fragmentContent);
		if (fragment == null) {
			fragment = new BrownMenuFragment();
			fm.beginTransaction().add(R.id.fragmentContent, fragment).commit();
		}	
        
	}

}
