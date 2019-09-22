package com.target.dealbrowserpoc.dealbrowser.deals;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/**
 *  Model class for Deal List API response
 */
public class DealsContent implements Parcelable {

    private String id;
    private List<DealItem> data = null;
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<DealItem> getData() {
        return data;
    }

    public void setData(List<DealItem> data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }
    protected DealsContent(Parcel in) {
        id = in.readString();
        data = in.createTypedArrayList(DealItem.CREATOR);
    }

    public static final Creator<DealsContent> CREATOR = new Creator<DealsContent>() {
        @Override
        public DealsContent createFromParcel(Parcel in) {
            return new DealsContent(in);
        }

        @Override
        public DealsContent[] newArray(int size) {
            return new DealsContent[size];
        }
    };

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(data);
    }

}



