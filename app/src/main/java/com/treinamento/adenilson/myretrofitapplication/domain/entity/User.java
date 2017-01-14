package com.treinamento.adenilson.myretrofitapplication.domain.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by adenilson on 11/01/2017.
 */

public class User implements Parcelable{

    public static final String USER_KEY = "USER_KEY";

    public String login;

    @SerializedName("avatar_url")
    public String avatarUrl;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.login);
        dest.writeString(this.avatarUrl);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.login = in.readString();
        this.avatarUrl = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
