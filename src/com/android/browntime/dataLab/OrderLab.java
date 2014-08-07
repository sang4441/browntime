package com.android.browntime.dataLab;

import android.content.Context;

import com.android.browntime.model.BrownOrder;

import java.util.ArrayList;
import java.util.List;

public class OrderLab {
    private BrownOrder mCurrentOrder;
	private List<BrownOrder> mOrders;
	private static OrderLab sBrownLab;
	private Context mAppContext;
	
	private OrderLab(Context appContext) {
		mAppContext = appContext;
		mOrders = new ArrayList<BrownOrder>();
        mCurrentOrder = new BrownOrder();
	}

	public static OrderLab  get(Context c) {
		if (sBrownLab == null) {
			sBrownLab = new OrderLab(c.getApplicationContext());
		}
		return sBrownLab;
	}
	
	public void addOrder(BrownOrder order) {
//        for (BrownOrder oldOrder : mOrders) {
//            mOrders.remove(oldOrder);
//        }
        mCurrentOrder = order;
//        mOrders.removeAll(mOrders);
//		mOrders.add(order);
	}
    public List<BrownOrder> getOrders() { return mOrders; }

    public BrownOrder getOrder(int orderId) {
        for (BrownOrder c: mOrders) {
            if (c.getmId()==orderId)
                return c;
        }
        return null;
    }
    public void setOrders(List<BrownOrder> orders) {
        mOrders = orders;
//        mOrders = new LinkedList(Arrays.asList(orders));
//        mOrders = new ArrayList<BrownOrder>(Arrays.asList(orders));
    }

    public BrownOrder getLastOrder() {
		return mCurrentOrder;
	}
	
}
