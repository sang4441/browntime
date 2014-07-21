package com.android.browntime;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class BrownOrder implements Serializable {

	private UUID mId;
	private ArrayList<BrownCart> mCarts;

    public int getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(int seller_id) {
        this.seller_id = seller_id;
    }


    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private int seller_id;
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

//	BrownOrder(Parcel in) {
//        this.mCarts = new ArrayList<BrownCart>();
//        this.mPrice = in.readInt();
//        this.mType = in.readInt();
//        in.readTypedList(mCarts, BrownCart);
//        
//        this.favoriteFloat = in.readFloat();
//    }
// 
//    void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(name);
//        dest.writeInt(yearsOfExperience);
//        dest.writeTypedList(skillSet);
//        dest.writeFloat(favoriteFloat);
//    }
// 
//    int describeContents() {
//        return 0;
//    }
//    
//    static final Parcelable.Creator<BrownOrder> CREATOR
//	    = new Parcelable.Creator<BrownOrder>() {
//	
//	BrownOrder createFromParcel(Parcel in) {
//	    return new BrownOrder(in);
//	}
//	
//	BrownOrder[] newArray(int size) {
//	    return new BrownOrder[size];
//	}
//	};
}
