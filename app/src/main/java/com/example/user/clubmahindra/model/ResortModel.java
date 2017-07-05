package com.example.user.clubmahindra.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by user on 28/6/17.
 */

public class ResortModel implements Parcelable {
    public static final Creator<ResortModel> CREATOR = new Creator<ResortModel>() {
        @Override
        public ResortModel createFromParcel(Parcel in) {
            return new ResortModel(in);
        }

        @Override
        public ResortModel[] newArray(int size) {
            return new ResortModel[size];
        }
    };
    int status;
    int responseCode;
    String responseMessage;
    String totalCount;
    ArrayList<Resorts> resorts;

    protected ResortModel(Parcel in) {
        status = in.readInt();
        responseCode = in.readInt();
        responseMessage = in.readString();
        totalCount = in.readString();
        resorts = in.createTypedArrayList(Resorts.CREATOR);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public ArrayList<Resorts> getResorts() {
        return resorts;
    }

    public void setResorts(ArrayList<Resorts> resorts) {
        this.resorts = resorts;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(status);
        parcel.writeInt(responseCode);
        parcel.writeString(responseMessage);
        parcel.writeString(totalCount);
        parcel.writeTypedList(resorts);
    }
}
