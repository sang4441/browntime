package com.android.browntime;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;

public class MenuLab {
	
	private ArrayList<BrownMenu> mMenus;
	private static MenuLab sBrownLab;
	private Context mAppContext;
	
	private MenuLab(Context appContext) {
		mAppContext = appContext;
		mMenus = new ArrayList<BrownMenu>();

		mMenus.add(new BrownMenu(R.string.americano, 5500));
		mMenus.add(new BrownMenu(R.string.korean_roll, 6000));
		mMenus.add(new BrownMenu(R.string.caffe_latte, 5000));

	}

	public static MenuLab get(Context c) {
		if (sBrownLab == null) {
			sBrownLab = new MenuLab(c.getApplicationContext());
		}
		return sBrownLab;
	}
	
	public ArrayList<BrownMenu> getMenus() {
		return mMenus;
	}
	
	public BrownMenu getMenu(UUID id) {
		for (BrownMenu c: mMenus) {
			if (c.getId().equals(id))
				return c;
		}
		return null;
	}
}
