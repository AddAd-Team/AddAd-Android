package com.add.ad.entity;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("userinfo")
    private String userType;
    @SerializedName("email")
    private String userEmail;
    @SerializedName("password")
    private String userPassword;
    @SerializedName("name")
    private String nick;
    @SerializedName("code")
    private String verifyCode;
    @SerializedName("tag")
    private String tag;

    public User(String userType, String userEmail, String userPassword, String nick, String verifyCode, String tag) {
        this.userType = userType;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.nick = nick;
        this.verifyCode = verifyCode;
        this.tag = tag;
    }

}
