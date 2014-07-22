package com.android.browntime;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class BrownMenu {

    @JsonProperty("id")
	private UUID mId;

    public UUID getmId() {
        return mId;
    }

    public void setmId(UUID mId) {
        this.mId = mId;
    }

    public int getmName() {
        return mName;
    }

    public void setmName(int mName) {
        this.mName = mName;
    }

    public int getmPrice() {
        return mPrice;
    }

    public void setmPrice(int mPrice) {
        this.mPrice = mPrice;
    }

    public int getmCategory() {
        return mCategory;
    }

    public void setmCategory(int mCategory) {
        this.mCategory = mCategory;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    @JsonProperty("name")
	private int mName;
    @JsonProperty("price")
	private int mPrice;
    @JsonProperty("category")
	private int mCategory;
    @JsonProperty("description")
	private String mDescription;

	public BrownMenu() {
		mId = UUID.randomUUID();
	}
	
	public BrownMenu(int name, int price, int type) {
		mId = UUID.randomUUID();
		mName = name;
		mPrice = price;
        mCategory = type;
	}
	
	public BrownMenu(int name, int price, int type, String description) {
		mId = UUID.randomUUID();
		mName = name;
		mPrice = price;
        mCategory = type;
		mDescription = description;
	}

}
