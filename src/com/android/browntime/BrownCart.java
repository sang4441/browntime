package com.android.browntime;


import com.fasterxml.jackson.annotation.JsonProperty;

public class BrownCart extends BrownMenu {

    @JsonProperty("priceTotal")
    private int mPriceTotal;
    @JsonProperty("quantity")
    private int mQuantity;
    @JsonProperty("instruction")
    private String mInstruction;

    public int getmPriceTotal() {
        return mPriceTotal;
    }

    public void setmPriceTotal(int mPriceTotal) {
        this.mPriceTotal = mPriceTotal;
    }

    public int getmQuantity() {
        return mQuantity;
    }

    public void setmQuantity(int mQuantity) {
        this.mQuantity = mQuantity;
    }

    public String getmInstruction() {
        return mInstruction;
    }

    public void setmInstruction(String mInstruction) {
        this.mInstruction = mInstruction;
    }

	public BrownCart() {
		super();
	}

	public BrownCart(int name, int price, int type, String description) {
		super(name, price, type, description);
	}
}
