package com.add.ad.entity.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResponseSearchInfo {
    @SerializedName("id")
    private int userId;
    @SerializedName("email")
    private String userEmail;
    @SerializedName("description")
    private String userDescription;
    @SerializedName("profileImg")
    private String profileImage;
    @SerializedName("name")
    private String userName;
    @SerializedName("hashtag")
    private String userTag;
    private ArrayList<ResponseDetailContactAdInfo> contactAd;

    public int getUserId() {
        return userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserDescription() {
        return userDescription;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserTag() {
        return userTag;
    }

    public ArrayList<ResponseDetailContactAdInfo> getContactAd() {
        return contactAd;
    }
}
