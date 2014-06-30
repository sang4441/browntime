package com.android.browntime;

import java.util.UUID;

public class BrownMenu {

	private UUID mId;
	private int mName;
	private int mPrice;
	private int mQuantity;
	
	public BrownMenu() {
		mId = UUID.randomUUID();
	}
	
	public BrownMenu(int name, int price) {
		mId = UUID.randomUUID();
		mName = name;
		mPrice = price;
	}
	
	public UUID getId() {
		return mId;
	}
	public void setId(UUID id) {
		mId = id;
	}
	public int getName() {
		return mName;
	}
	public void setName(int name) {
		mName = name;
	}
	public int getPrice() {
		return mPrice;
	}
	public void setPrice(int price) {
		mPrice = price;
	}
	
//	@Override
//	public String toString() {
//		return mName;
//	}
}
