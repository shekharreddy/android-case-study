package com.target.dealbrowserpoc.dealbrowser.deals;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Model class for DealItem, single product information
 */

public class DealItem implements Parcelable {

    @SerializedName("_id")
    private String id;
    private String aisle;
    private String description;
    private String guid;
    private String image;
    private Integer index;
    private String price;
    private String salePrice;
    private String title;

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(id);
        out.writeString(aisle);
        out.writeString(description);
        out.writeString(image);
        out.writeString(price);
        out.writeString(salePrice);
        out.writeString(title);
    }

    public static final Parcelable.Creator<DealItem> CREATOR
            = new Parcelable.Creator<DealItem>() {
        public DealItem createFromParcel(Parcel in) {
            return new DealItem(in);
        }

        public DealItem[] newArray(int size) {
            return new DealItem[size];
        }
    };

    private DealItem(Parcel in) {
        id = in.readString();
        aisle = in.readString();
        description = in.readString();
        image = in.readString();
        price = in.readString();
        salePrice = in.readString();
        title = in.readString();
    }

    public String getId() {
        return id == null ? "" : id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAisle() {
        return aisle == null ? "" : aisle.toUpperCase();
    }

    public void setAisle(String aisle) {
        this.aisle = aisle;
    }

    public String getDescription() {
        return description == null ? "" : description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getImage() {
        return image == null ? "" : image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getPrice() {
        return price == null ? "" :price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSalePrice() {
        return salePrice == null ? "" : salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getTitle() {
        return title == null ? "" : title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}