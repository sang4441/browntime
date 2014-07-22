package com.android.browntime;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

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
			total = total + (menu.getmPrice() * menu.getmQuantity());
		}
		return total;
	}
	
	public ArrayList<BrownCart> getMenus() {
		return mMenus;
	}
	
	public void clearCart() {
		mMenus.clear();
	}
	
	public void deleteCart(UUID id) {		
		for (int i = 0; i < mMenus.size(); i++) {
			if (mMenus.get(i).getmId().equals(id)) {
				mMenus.remove(i);
			}
		}
	}
	
}
