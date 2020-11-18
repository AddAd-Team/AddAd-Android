package com.add.ad.entity;

import com.google.gson.annotations.SerializedName;

public class Token {
    private String accessToken;
    private String refreshToken;
    @SerializedName("userinfo")
    private String userInfo;

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getUserInfo() {
        return userInfo;
    }
}
