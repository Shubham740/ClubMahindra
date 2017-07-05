package com.example.user.clubmahindra.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user on 28/6/17.
 */

public class TokenDataModel implements Parcelable {
    public static final Creator<TokenDataModel> CREATOR = new Creator<TokenDataModel>() {
        @Override
        public TokenDataModel createFromParcel(Parcel in) {
            return new TokenDataModel(in);
        }

        @Override
        public TokenDataModel[] newArray(int size) {
            return new TokenDataModel[size];
        }
    };
    String userUniqueId;
    String authToken;

    protected TokenDataModel(Parcel in) {
        userUniqueId = in.readString();
        authToken = in.readString();
    }

    public String getUserUniqueId() {
        return userUniqueId;
    }

    public void setUserUniqueId(String userUniqueId) {
        this.userUniqueId = userUniqueId;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(userUniqueId);
        parcel.writeString(authToken);
    }
}
