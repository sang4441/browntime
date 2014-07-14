package com.android.browntime;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;

public class CartLab {
	private ArrayList<BrownCart> mMenus;
	private static CartLab sBrownLab;
	private Context mAppContext;
	
	private CartLab(Context appContext) {
		mAppContext = appContext;
		mMenus = new ArrayList<BrownCart>();
		
		mMenus.add(new BrownCart(R.string.americano, 5500, 1));
//		mMenus.add(new BrownCart(R.string.caffe_latte, 5500, 1));
//		mMenus.add(new BrownCart(R.string.capucino, 5000, 1));
	}

	public static CartLab get(Context c) {
		if (sBrownLab == null) {
			sBrownLab = new CartLab(c.getApplicationContext());
		}
		return sBrownLab;
	}
	
	public void addMenu(BrownCart menu) {
		mMenus.add(menu);
	}
	
	public int getPriceTotal() {
		int total = 0;
		for (BrownCart menu : mMenus) {
			total = total + menu.getTotalPrice();
		}
		return total;
	}
	
	public ArrayList<BrownCart> getMenus() {
		return mMenus;
	}
	
//	
//	public ArrayList<BrownCart> getMenus(int type) {
//		ArrayList<BrownCart> menuByType = new ArrayList<BrownCart>();
//		for (BrownCart menus : mMenus) {
//			if (menus.getType() == type) {
//				menuByType.add(menus);
//			}
//		}
//		return menuByType;
//	}
//	
//	public BrownCart getMenu(UUID id) {
//		for (BrownCart c: mMenus) {
//			if (c.getId().equals(id))
//				return c;
//		}
//		return null;
//	}
}
