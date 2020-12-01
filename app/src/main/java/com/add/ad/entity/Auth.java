package com.add.ad.entity;

import com.google.gson.annotations.SerializedName;

public class Auth {
    @SerializedName("email")
    private String userEmail;
    @SerializedName("password")
    private String userPassword;
    @SerializedName("deviceToken")
    private String deviceToken;

    public Auth(String userEmail, String userPassword, String deviceToken){
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.deviceToken = deviceToken;
    }
}
