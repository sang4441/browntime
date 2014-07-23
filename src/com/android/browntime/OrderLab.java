package com.android.browntime;

import java.util.ArrayList;

import android.content.Context;

import com.android.browntime.model.BrownOrder;

public class OrderLab {
	private ArrayList<BrownOrder> mOrders;
	private static OrderLab sBrownLab;
	private Context mAppContext;
	
	private OrderLab(Context appContext) {
		mAppContext = appContext;
		mOrders = new ArrayList<BrownOrder>();
	}

	public static OrderLab get(Context c) {
		if (sBrownLab == null) {
			sBrownLab = new OrderLab(c.getApplicationContext());
		}
		return sBrownLab;
	}
	
	public void addOrder(BrownOrder order) {
		mOrders.add(order);
	}
	
	public BrownOrder getLastOrder() {
		return mOrders.get(mOrders.size()-1);
	}
	
}
