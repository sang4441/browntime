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

		mMenus.add(new BrownMenu(R.string.americano, 5500, 1));
		mMenus.add(new BrownMenu(R.string.caffe_latte, 5500, 1));
		mMenus.add(new BrownMenu(R.string.capucino, 5000, 1));
		mMenus.add(new BrownMenu(R.string.iced_americano, 5500, 1));
		mMenus.add(new BrownMenu(R.string.iced_cappucino, 5000, 1));
		mMenus.add(new BrownMenu(R.string.iced_latte, 6500, 1));
	
		
		mMenus.add(new BrownMenu(R.string.latte_1, 6000, 2));
		mMenus.add(new BrownMenu(R.string.latte_2, 4500, 2));
		mMenus.add(new BrownMenu(R.string.latte_3, 4500, 2));
		mMenus.add(new BrownMenu(R.string.latte_4, 4500, 2));
		mMenus.add(new BrownMenu(R.string.latte_5, 4500, 2));
		mMenus.add(new BrownMenu(R.string.latte_6, 4500, 2));
		
		
		mMenus.add(new BrownMenu(R.string.korean_roll, 5000, 3));
		mMenus.add(new BrownMenu(R.string.korean_roll_2, 5500, 3));
		mMenus.add(new BrownMenu(R.string.pizza_1, 5500, 3));
		mMenus.add(new BrownMenu(R.string.pizza_2, 5500, 3));
		mMenus.add(new BrownMenu(R.string.sandwich_1, 5500, 3));
		mMenus.add(new BrownMenu(R.string.sandwich_2, 5500, 3));
		

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
	
	public ArrayList<BrownMenu> getMenus(int type) {
		ArrayList<BrownMenu> menuByType = new ArrayList<BrownMenu>();
		for (BrownMenu menus : mMenus) {
			if (menus.getType() == type) {
				menuByType.add(menus);
			}
		}
		return menuByType;
	}
	
	public BrownMenu getMenu(UUID id) {
		for (BrownMenu c: mMenus) {
			if (c.getId().equals(id))
				return c;
		}
		return null;
	}
}
