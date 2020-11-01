package com.add.ad.entity;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("email")
    private String userEmail;
    @SerializedName("password")
    private String userPassword;
    @SerializedName("nickname")
    private String nick;
    @SerializedName("code")
    private String verifyCode;
    @SerializedName("tag")
    private String tag;

    public User(String userEmail, String userPassword, String nick, String tag) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.nick = nick;
        this.tag = tag;
    }

    public User(String userEmail, String verifyCode) {
        this.verifyCode = verifyCode;
        this.userEmail = userEmail;
    }

    public User(String userEmail){
        this.userEmail = userEmail;
    }
}
