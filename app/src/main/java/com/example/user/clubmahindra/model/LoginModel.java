package com.example.user.clubmahindra.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user on 28/6/17.
 */

public class LoginModel implements Parcelable {
    public static final Creator<LoginModel> CREATOR = new Creator<LoginModel>() {
        @Override
        public LoginModel createFromParcel(Parcel in) {
            return new LoginModel(in);
        }

        @Override
        public LoginModel[] newArray(int size) {
            return new LoginModel[size];
        }
    };
    int status;
    int responseCode;
    String responseMessage;
    TokenDataModel data;

    protected LoginModel(Parcel in) {
        status = in.readInt();
        responseCode = in.readInt();
        responseMessage = in.readString();
        data = in.readParcelable(TokenDataModel.class.getClassLoader());
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

    public TokenDataModel getData() {
        return data;
    }

    public void setData(TokenDataModel data) {
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
