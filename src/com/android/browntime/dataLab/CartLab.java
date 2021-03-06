package com.android.browntime.dataLab;

import android.content.Context;

import com.android.browntime.model.BrownCart;

import java.util.ArrayList;

public class CartLab {
	private ArrayList<BrownCart> mCarts;
	private static CartLab sBrownLab;
	private Context mAppContext;
	
	private CartLab(Context appContext) {
		mAppContext = appContext;
		mCarts = new ArrayList<BrownCart>();

//        mCarts.add(new BrownCart(R.string.americano, 5500, 1, "description"));
//		mMenus.add(new BrownCart(R.string.caffe_latte, 5500, 1));
//		mMenus.add(new BrownCart(R.string.capucino, 5000, 1));
	}

	public static CartLab get(Context c) {
		if (sBrownLab == null) {
			sBrownLab = new CartLab(c.getApplicationContext());
		}
		return sBrownLab;
	}
	
	public void addCart(BrownCart cart) {
        mCarts.add(cart);
	}
	
	public int getPriceTotal() {
		int total = 0;
		for (BrownCart cart : mCarts) {
//            int cartTotal = cart.getmPriceTotal();
			total = total + cart.getmPriceTotal();
		}
		return total;
	}
	
	public ArrayList<BrownCart> getMenus() {
		return mCarts;
	}

    public ArrayList<BrownCart> getCarts() {
        return mCarts;
    }
	
	public void clearCart() {
        mCarts.clear();
	}
	
	public void deleteCart(int id) {
		for (int i = 0; i < mCarts.size(); i++) {
			if (mCarts.get(i).getmId()==id) {
                mCarts.remove(i);
			}
		}
	}
	
}
