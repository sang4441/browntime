package com.android.browntime;

import java.util.UUID;

public class BrownMenu {

	private UUID mId;
	private int mName;
	private int mPrice;
	private int mQuantity;
	private int mType;
	private String mDescription;

	public BrownMenu() {
		mId = UUID.randomUUID();
		mQuantity = 1;
	}
	
	public BrownMenu(int name, int price, int type) {
		mId = UUID.randomUUID();
		mName = name;
		mPrice = price;
		mQuantity = 1;
		mType = type;
	}
	
	public BrownMenu(int name, int price, int type, String description) {
		mId = UUID.randomUUID();
		mName = name;
		mPrice = price;
		mQuantity = 1;
		mType = type;
		mDescription = description;
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
	
	public int getQuantity() {
		return mQuantity;
	}

	public void setQuantity(int quantity) {
		mQuantity = quantity;
	}	
	
	public int getType() {
		return mType;
	}

	public String getDescription() {
		return mDescription;
	}

	public void setDescription(String description) {
		mDescription = description;
	}


//	@Override
//	public String toString() {
//		return mName;
//	}
}
