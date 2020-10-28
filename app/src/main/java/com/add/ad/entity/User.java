package com.add.ad.entity;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("email")
    private String userEmail;
    @SerializedName("password")
    private String userPassword;
    @SerializedName("verify_code")
    private String verifyCode;
    @SerializedName("nickname")
    private String nickname;
    @SerializedName("tag")
    private String tag;

    public User(String userEmail, String userPassword, String verifyCode, String nickname, String tag){
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.verifyCode = verifyCode;
        this.nickname = nickname;
        this.tag = tag;
    }
}
