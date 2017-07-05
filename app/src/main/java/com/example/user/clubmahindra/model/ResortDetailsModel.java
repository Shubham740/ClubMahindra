package com.example.user.clubmahindra.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user on 28/6/17.
 */

public class ResortDetailsModel implements Parcelable

{
    public static final Creator<ResortDetailsModel> CREATOR = new Creator<ResortDetailsModel>() {
        @Override
        public ResortDetailsModel createFromParcel(Parcel in) {
            return new ResortDetailsModel(in);
        }

        @Override
        public ResortDetailsModel[] newArray(int size) {
            return new ResortDetailsModel[size];
        }
    };
    int status;
    int responseCode;
    String responseMessage;
    AboutResortModel data;

    protected ResortDetailsModel(Parcel in) {
        status = in.readInt();
        responseCode = in.readInt();
        responseMessage = in.readString();
        data = in.readParcelable(AboutResortModel.class.getClassLoader());
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

    public AboutResortModel getData() {
        return data;
    }

    public void setData(AboutResortModel data) {
        this.data = data;
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
        parcel.writeParcelable(data, i);
    }
}
