package com.android.browntime.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by kimsanghwan on 7/30/2014.
 */
public class BrownBuyer {

    public int getmBuyerId() {
        return mBuyerId;
    }

    public void setmBuyerId(int mBuyerId) {
        this.mBuyerId = mBuyerId;
    }

    @JsonProperty("buyerId")
    private int mBuyerId;
    @JsonProperty("buyerName")
    private String mBuyerName;
    @JsonProperty("buyerCellNumber")
    private int mBuyerCellNumber;

    public String getmBuyerName() {
        return mBuyerName;
    }

    public void setmBuyerName(String mBuyerName) {
        this.mBuyerName = mBuyerName;
    }

    public int getmBuyerCellNumber() {
        return mBuyerCellNumber;
    }

    public void setmBuyerCellNumber(int mBuyerCellNumber) {
        this.mBuyerCellNumber = mBuyerCellNumber;
    }
}
