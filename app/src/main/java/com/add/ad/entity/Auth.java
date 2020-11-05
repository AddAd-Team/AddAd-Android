package com.add.ad.entity;

import com.google.gson.annotations.SerializedName;

public class Auth {
    @SerializedName("email")
    private String userEmail;
    @SerializedName("password")
    private String userPassword;

    public Auth(String userEmail, String userPassword){
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }
}
