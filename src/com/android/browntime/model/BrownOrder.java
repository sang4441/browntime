package com.android.browntime.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Date;

public class BrownOrder extends BrownBuyer {

    @JsonProperty("id")
    private int mId;
    @JsonProperty("sellerId")
    private int mSellerId;
    @JsonProperty("typeId")
    private int mType;
    @JsonProperty("typeName")
    private String mTypeName;
    @JsonProperty("address")
    private String mAddress;
    @JsonProperty("price")
    private int mPrice;
    @JsonProperty("duration")
    private int mDuration;
    @JsonProperty("timeRequested")
    private Date mTime;
    @JsonProperty("statusId")
    private int mStatusId;
    @JsonProperty("statusName")
    private String mStatusName;
    @JsonProperty("carts")
    private ArrayList<BrownCart> mCarts;


    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }
    public int getmSellerId() {
        return mSellerId;
    }

    public void setmSellerId(int mSellerId) {
        this.mSellerId = mSellerId;
    }

    public int getmType() {
        return mType;
    }

    public void setmType(int mType) {
        this.mType = mType;
    }

    public int getmPrice() {
        return mPrice;
    }

    public void setmPrice(int mPrice) {
        this.mPrice = mPrice;
    }

    public Date getmTime() {
        return mTime;
    }

    public void setmTime(Date mTime) {
        this.mTime = mTime;
    }

    public ArrayList<BrownCart> getmCarts() {
        return mCarts;
    }
    public void setmCarts(ArrayList<BrownCart> mCarts) {
        this.mCarts = mCarts;
    }


    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }


    public int getmStatusId() {
        return mStatusId;
    }

    public void setmStatusId(int mStatusId) {
        this.mStatusId = mStatusId;
    }

    public int getmDuration() {
        return mDuration;
    }

    public void setmDuration(int mDuration) {
        this.mDuration = mDuration;
    }

    public String getmTypeName() {
        return mTypeName;
    }

    public void setmTypeName(String mTypeName) {
        this.mTypeName = mTypeName;
    }

    public String getmStatusName() {
        return mStatusName;
    }

    public void setmStatusName(String mStatusName) {
        this.mStatusName = mStatusName;
    }

//    @JsonProperty("id")
//    private UUID mId;
//    @JsonProperty("sellerId")
//    private int mSellerId;
//    @JsonProperty("typeId")
//    private int mType;
//    @JsonProperty("price")
//    private int mPrice;
//    @JsonProperty("timeRequested")
//	private Date mTime;
//    @JsonProperty("carts")
//    private ArrayList<BrownCart> mCarts;
//
//    public BrownOrder() {
//		mId = UUID.randomUUID();
//	}
//
//	public UUID getId() {
//		return mId;
//	}
//	public void setId(UUID id) {
//		mId = id;
//	}
//	public ArrayList<BrownCart> getCarts() {
//		return mCarts;
//	}
//	public void setCarts(ArrayList<BrownCart> carts) {
//		mCarts = carts;
//	}
//	public int getPrice() {
//		return mPrice;
//	}
//	public void setPrice(int price) {
//		mPrice = price;
//	}
//	public int getType() {
//		return mType;
//	}
//	public void setType(int type) {
//		mType = type;
//	}
//	public Date getTime() {
//		return mTime;
//	}
//	public void setTime(Date time) {
//		mTime = time;
//	}
//
//    public int getSellerId() {
//        return mSellerId;
//    }
//
//    public void setSellerId(int mSellerId) {
//        this.mSellerId = mSellerId;
//    }
}
