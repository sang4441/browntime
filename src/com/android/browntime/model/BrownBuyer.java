package com.android.browntime.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by kimsanghwan on 7/30/2014.
 */
public class BrownBuyer {


    @JsonProperty("buyerId")
    private int mBuyerId;
    @JsonProperty("buyerName")
    private String mBuyerName;
    @JsonProperty("buyerCellNumber")
    private String mBuyerCellNumber;
    @JsonProperty("isCertified")
    private int mIsCertified;
    @JsonProperty("smsNumber")
    private int mSMSNumber;
    @JsonProperty("smsFlag")
    private int mSMSFlag;

    public String getmBuyerName() {
        return mBuyerName;
    }

    public void setmBuyerName(String mBuyerName) {
        this.mBuyerName = mBuyerName;
    }


    public int getmBuyerId() {
        return mBuyerId;
    }

    public void setmBuyerId(int mBuyerId) {
        this.mBuyerId = mBuyerId;
    }

    public String getmBuyerCellNumber() {
        return mBuyerCellNumber;
    }

    public void setmBuyerCellNumber(String mBuyerCellNumber) {
        this.mBuyerCellNumber = mBuyerCellNumber;
    }

    public int getmIsCertified() {
        return mIsCertified;
    }

    public void setmIsCertified(int mIsCertified) {
        this.mIsCertified = mIsCertified;
    }

    public int getmSMSNumber() {
        return mSMSNumber;
    }

    public void setmSMSNumber(int mSMSNumber) {
        this.mSMSNumber = mSMSNumber;
    }

    public int getmSMSFlag() {
        return mSMSFlag;
    }

    public void setmSMSFlag(int mSMSFlag) {
        this.mSMSFlag = mSMSFlag;
    }
}
