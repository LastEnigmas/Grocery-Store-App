package com.example.ariagrocery;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.ariagrocery.dataBase.ReviewsConverter;

import java.util.ArrayList;


@Entity(tableName = "grocery_item")
public class GrocerItem implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String description;
    private String imageUrl;
    private String Category;
    private double price;
    private int availableAmount;
    private int rate;
    private int userpoint;
    private int popularitypoint;

    @TypeConverters(ReviewsConverter.class)
    private ArrayList<Review> reviews;

    public GrocerItem(String name, String description, String imageUrl, String category, double price, int availableAmount, int rate, int userpoint, int popularitypoint, ArrayList<Review> reviews) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        Category = category;
        this.price = price;
        this.availableAmount = availableAmount;
        this.rate = rate;
        this.userpoint = userpoint;
        this.popularitypoint = popularitypoint;
        this.reviews = reviews;
    }

    public GrocerItem() {
    }

    @Ignore
    public GrocerItem( String name, String description, String imageUrl, String category, double price, int availableAmount) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        Category = category;
        this.price = price;
        this.availableAmount = availableAmount;
        this.rate = 0;
        this.userpoint = 0;
        this.popularitypoint = 0;
        this.reviews = new ArrayList<>();
    }



    @Ignore
    protected GrocerItem(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
        imageUrl = in.readString();
        Category = in.readString();
        price = in.readDouble();
        availableAmount = in.readInt();
        rate = in.readInt();
        userpoint = in.readInt();
        popularitypoint = in.readInt();
    }

    @Ignore
    public static final Creator<GrocerItem> CREATOR = new Creator<GrocerItem>() {
        @Override
        public GrocerItem createFromParcel(Parcel in) {
            return new GrocerItem(in);
        }

        @Override
        public GrocerItem[] newArray(int size) {
            return new GrocerItem[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(int availableAmount) {
        this.availableAmount = availableAmount;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getUserpoint() {
        return userpoint;
    }

    public void setUserpoint(int userpoint) {
        this.userpoint = userpoint;
    }

    public int getPopularitypoint() {
        return popularitypoint;
    }

    public void setPopularitypoint(int popularitypoint) {
        this.popularitypoint = popularitypoint;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    @Ignore
    @Override
    public String toString() {
        return "GrocerItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", Category='" + Category + '\'' +
                ", price=" + price +
                ", availableAmount=" + availableAmount +
                ", rate=" + rate +
                ", userpoint=" + userpoint +
                ", popularitypoint=" + popularitypoint +
                ", reviews=" + reviews +
                '}';
    }


    @Ignore
    @Override
    public int describeContents() {
        return 0;
    }


    @Ignore
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeString(imageUrl);
        parcel.writeString(Category);
        parcel.writeDouble(price);
        parcel.writeInt(availableAmount);
        parcel.writeInt(rate);
        parcel.writeInt(userpoint);
        parcel.writeInt(popularitypoint);
    }
}
