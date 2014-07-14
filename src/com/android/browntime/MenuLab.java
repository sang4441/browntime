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

		mMenus.add(new BrownMenu(R.string.espresso, 5500, 1, "this is a description for coffee this is a description for coffee this is a description for coffee"));
		mMenus.add(new BrownMenu(R.string.americano, 5500, 1, "this is a description for coffee this is a description for coffee this is a description for coffee"));
		mMenus.add(new BrownMenu(R.string.latte_cafe, 5500, 1, "this is a description for coffee this is a description for coffee this is a description for coffee"));
		mMenus.add(new BrownMenu(R.string.latte_cube, 5500, 1, "this is a description for coffee this is a description for coffee this is a description for coffee"));
		mMenus.add(new BrownMenu(R.string.capucino, 5500, 1, "this is a description for coffee this is a description for coffee this is a description for coffee"));
		mMenus.add(new BrownMenu(R.string.caffe_latte, 5500, 1, "this is a description for coffee this is a description for coffee this is a description for coffee"));
		mMenus.add(new BrownMenu(R.string.caffe_moka, 5000, 1, "this is a description for coffee this is a description for coffee this is a description for coffee"));
		mMenus.add(new BrownMenu(R.string.latte_caramel, 5500, 1, "this is a description for coffee this is a description for coffee this is a description for coffee"));
		mMenus.add(new BrownMenu(R.string.latte_vanilla, 5000, 1, "this is a description for coffee this is a description for coffee this is a description for coffee"));
		mMenus.add(new BrownMenu(R.string.con_panna, 6500, 1, "this is a description for coffee this is a description for coffee this is a description for coffee"));
		mMenus.add(new BrownMenu(R.string.vienna, 6500, 1, "this is a description for coffee this is a description for coffee this is a description for coffee"));

		mMenus.add(new BrownMenu(R.string.shakerato, 5500, 1, "this is a description for coffee this is a description for coffee this is a description for coffee"));
		mMenus.add(new BrownMenu(R.string.iced_americano, 5500, 1, "this is a description for coffee this is a description for coffee this is a description for coffee"));
		mMenus.add(new BrownMenu(R.string.iced_latte_cafe, 5500, 1, "this is a description for coffee this is a description for coffee this is a description for coffee"));
		mMenus.add(new BrownMenu(R.string.iced_cappucino, 5500, 1, "this is a description for coffee this is a description for coffee this is a description for coffee"));
		mMenus.add(new BrownMenu(R.string.iced_caffe_moka, 5500, 1, "this is a description for coffee this is a description for coffee this is a description for coffee"));
		mMenus.add(new BrownMenu(R.string.iced_latte_caramel, 5500, 1, "this is a description for coffee this is a description for coffee this is a description for coffee"));
		mMenus.add(new BrownMenu(R.string.iced_latte_vanilla, 5000, 1, "this is a description for coffee this is a description for coffee this is a description for coffee"));
		mMenus.add(new BrownMenu(R.string.dutch_coffe, 5500, 1, "this is a description for coffee this is a description for coffee this is a description for coffee"));
		mMenus.add(new BrownMenu(R.string.dutch_latte, 5000, 1, "this is a description for coffee this is a description for coffee this is a description for coffee"));
		mMenus.add(new BrownMenu(R.string.affogato, 6500, 1, "this is a description for coffee this is a description for coffee this is a description for coffee"));
				
		mMenus.add(new BrownMenu(R.string.latte_chocolate, 6000, 2, "this is a description for coffee"));
		mMenus.add(new BrownMenu(R.string.latte_yam, 4500, 2, "this is a description for coffee"));
		mMenus.add(new BrownMenu(R.string.latte_greentea, 4500, 2, "this is a description for coffee"));
		mMenus.add(new BrownMenu(R.string.latte_grain, 4500, 2, "this is a description for coffee"));
		mMenus.add(new BrownMenu(R.string.latte_chai, 4500, 2, "this is a description for coffee"));
		mMenus.add(new BrownMenu(R.string.tea_milk, 4500, 2, "this is a description for coffee"));
		
		
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
