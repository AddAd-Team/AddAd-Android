package com.add.ad.entity.response;

import com.google.gson.annotations.SerializedName;

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
}
