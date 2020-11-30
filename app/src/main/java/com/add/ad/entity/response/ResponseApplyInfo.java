package com.add.ad.entity.response;

import com.google.gson.annotations.SerializedName;

import retrofit2.SkipCallbackExecutor;

public class ResponseApplyInfo {
    @SerializedName("user_id")
    private int userId;
    @SerializedName("name")
    private String userName;
    @SerializedName("profileImg")
    private String profileImage;
    private String deviceToken;

    public String getDeviceToken() {
        return deviceToken;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getProfileImage() {
        return profileImage;
    }
}