package com.example.user.clubmahindra.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by user on 28/6/17.
 */

public class Resorts implements Parcelable {
    public static final Creator<Resorts> CREATOR = new Creator<Resorts>() {
        @Override
        public Resorts createFromParcel(Parcel in) {
            return new Resorts(in);
        }

        @Override
        public Resorts[] newArray(int size) {
            return new Resorts[size];
        }
    };
    String resortid;
    String avail;
    String reviewcount;
    String nextAvailableDate;
    String ResortShortname;
    String State;
    String offers;
    String Terrain;
    ArrayList RoomType;
    ArrayList<String> AboutImgURL;

    protected Resorts(Parcel in) {
        resortid = in.readString();
        avail = in.readString();
        reviewcount = in.readString();
        nextAvailableDate = in.readString();
        ResortShortname = in.readString();
        State = in.readString();
        offers = in.readString();
        Terrain = in.readString();
    }

    public String getResortid() {
        return resortid;
    }

    public void setResortid(String resortid) {
        this.resortid = resortid;
    }

    public String getAvail() {
        return avail;
    }

    public void setAvail(String avail) {
        this.avail = avail;
    }

    public String getReviewcount() {
        return reviewcount;
    }

    public void setReviewcount(String reviewcount) {
        this.reviewcount = reviewcount;
    }

    public String getNextAvailableDate() {
        return nextAvailableDate;
    }

    public void setNextAvailableDate(String nextAvailableDate) {
        this.nextAvailableDate = nextAvailableDate;
    }

    public String getResortShortname() {
        return ResortShortname;
    }

    public void setResortShortname(String resortShortname) {
        ResortShortname = resortShortname;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getOffers() {
        return offers;
    }

    public void setOffers(String offers) {
        this.offers = offers;
    }

    public String getTerrain() {
        return Terrain;
    }

    public void setTerrain(String terrain) {
        Terrain = terrain;
    }

    public ArrayList getRoomType() {
        return RoomType;
    }

    public void setRoomType(ArrayList roomType) {
        RoomType = roomType;
    }

    public ArrayList getAboutImgURL() {
        return AboutImgURL;
    }

    public void setAboutImgURL(ArrayList aboutImgURL) {
        AboutImgURL = aboutImgURL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(resortid);
        parcel.writeString(avail);
        parcel.writeString(reviewcount);
        parcel.writeString(nextAvailableDate);
        parcel.writeString(ResortShortname);
        parcel.writeString(State);
        parcel.writeString(offers);
        parcel.writeString(Terrain);
    }
}
