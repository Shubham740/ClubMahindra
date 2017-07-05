package com.example.user.clubmahindra.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user on 28/6/17.
 */

public class AmenitiesModel implements Parcelable {
    public static final Creator<AmenitiesModel> CREATOR = new Creator<AmenitiesModel>() {
        @Override
        public AmenitiesModel createFromParcel(Parcel in) {
            return new AmenitiesModel(in);
        }

        @Override
        public AmenitiesModel[] newArray(int size) {
            return new AmenitiesModel[size];
        }
    };
    String SrNo;
    String Name;
    String Icon;
    String inactive;

    protected AmenitiesModel(Parcel in) {
        SrNo = in.readString();
        Name = in.readString();
        Icon = in.readString();
        inactive = in.readString();
    }

    public String getSrNo() {
        return SrNo;
    }

    public void setSrNo(String srNo) {
        SrNo = srNo;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getIcon() {
        return Icon;
    }

    public void setIcon(String icon) {
        Icon = icon;
    }

    public String getInactive() {
        return inactive;
    }

    public void setInactive(String inactive) {
        this.inactive = inactive;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(SrNo);
        parcel.writeString(Name);
        parcel.writeString(Icon);
        parcel.writeString(inactive);
    }
}
