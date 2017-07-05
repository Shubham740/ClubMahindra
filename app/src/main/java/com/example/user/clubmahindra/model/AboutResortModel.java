package com.example.user.clubmahindra.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by user on 28/6/17.
 */

public class AboutResortModel implements Parcelable {
    public static final Creator<AboutResortModel> CREATOR = new Creator<AboutResortModel>() {
        @Override
        public AboutResortModel createFromParcel(Parcel in) {
            return new AboutResortModel(in);
        }

        @Override
        public AboutResortModel[] newArray(int size) {
            return new AboutResortModel[size];
        }
    };
    String ResortShortname;
    String ResortAddress;
    String Resort_id;
    String Phone;
    String Email;
    String AboutResort;
    String AboutVidURL;
    ArrayList<AmenitiesModel> Amenities;
    ArrayList<String> AboutImgURL;
    ArrayList<TempratureModel> Temperature;
    String BestTimetoTravel;

    protected AboutResortModel(Parcel in) {
        ResortShortname = in.readString();
        ResortAddress = in.readString();
        Resort_id = in.readString();
        Phone = in.readString();
        Email = in.readString();
        AboutResort = in.readString();
        AboutVidURL = in.readString();
        AboutImgURL = in.createStringArrayList();
        BestTimetoTravel = in.readString();
    }

    public String getResortShortname() {
        return ResortShortname;
    }

    public void setResortShortname(String resortShortname) {
        ResortShortname = resortShortname;
    }

    public String getResortAddress() {
        return ResortAddress;
    }

    public void setResortAddress(String resortAddress) {
        ResortAddress = resortAddress;
    }

    public String getResort_id() {
        return Resort_id;
    }

    public void setResort_id(String resort_id) {
        Resort_id = resort_id;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAboutResort() {
        return AboutResort;
    }

    public void setAboutResort(String aboutResort) {
        AboutResort = aboutResort;
    }

    public String getAboutVidURL() {
        return AboutVidURL;
    }

    public void setAboutVidURL(String aboutVidURL) {
        AboutVidURL = aboutVidURL;
    }

    public ArrayList<AmenitiesModel> getAmenities() {
        return Amenities;
    }

    public void setAmenities(ArrayList<AmenitiesModel> amenities) {
        Amenities = amenities;
    }

    public ArrayList<String> getAboutImgURL() {
        return AboutImgURL;
    }

    public void setAboutImgURL(ArrayList<String> aboutImgURL) {
        AboutImgURL = aboutImgURL;
    }

    public ArrayList<TempratureModel> getTemperature() {
        return Temperature;
    }

    public void setTemperature(ArrayList<TempratureModel> temperature) {
        Temperature = temperature;
    }

    public String getBestTimetoTravel() {
        return BestTimetoTravel;
    }

    public void setBestTimetoTravel(String bestTimetoTravel) {
        BestTimetoTravel = bestTimetoTravel;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(ResortShortname);
        parcel.writeString(ResortAddress);
        parcel.writeString(Resort_id);
        parcel.writeString(Phone);
        parcel.writeString(Email);
        parcel.writeString(AboutResort);
        parcel.writeString(AboutVidURL);
        parcel.writeStringList(AboutImgURL);
        parcel.writeString(BestTimetoTravel);
    }
}
