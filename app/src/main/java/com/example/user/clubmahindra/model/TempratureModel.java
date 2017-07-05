package com.example.user.clubmahindra.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user on 28/6/17.
 */

public class TempratureModel implements Parcelable {
    public static final Creator<TempratureModel> CREATOR = new Creator<TempratureModel>() {
        @Override
        public TempratureModel createFromParcel(Parcel in) {
            return new TempratureModel(in);
        }

        @Override
        public TempratureModel[] newArray(int size) {
            return new TempratureModel[size];
        }
    };
    String Summer;
    String Winter;

    protected TempratureModel(Parcel in) {
        Summer = in.readString();
        Winter = in.readString();
    }

    public String getSummer() {
        return Summer;
    }

    public void setSummer(String summer) {
        Summer = summer;
    }

    public String getWinter() {
        return Winter;
    }

    public void setWinter(String winter) {
        Winter = winter;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Summer);
        parcel.writeString(Winter);
    }
}
