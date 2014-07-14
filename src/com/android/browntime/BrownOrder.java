package com.android.browntime;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class BrownOrder {

	private UUID mId;
	private ArrayList<BrownCart> mCarts;
	private int mPrice;
	private int mType;
	private Date mTime;
	
	public BrownOrder() {
		mId = UUID.randomUUID();
	}
	
	public UUID getId() {
		return mId;
	}
	public void setId(UUID id) {
		mId = id;
	}
	public ArrayList<BrownCart> getCarts() {
		return mCarts;
	}
	public void setCarts(ArrayList<BrownCart> carts) {
		mCarts = carts;
	}
	public int getPrice() {
		return mPrice;
	}
	public void setPrice(int price) {
		mPrice = price;
	}
	public int getType() {
		return mType;
	}
	public void setType(int type) {
		mType = type;
	}
	public Date getTime() {
		return mTime;
	}
	public void setTime(Date time) {
		mTime = time;
	}
	
}
