package com.android.browntime;


public class BrownCart extends BrownMenu {

	private int mPriceTotal;

	public BrownCart() {
		super();
	}
	
	public BrownCart(int name, int price, int type) {
		super(name, price, type);
	}
	
//	public int getPriceTotal() {
//		return mPriceTotal;
//	}
//
//	public void setPriceTotal(int priceTotal) {
//		mPriceTotal = priceTotal;
//	}
	
	public int getTotalPrice() {
		return this.getQuantity() * this.getPrice();
	}
}
