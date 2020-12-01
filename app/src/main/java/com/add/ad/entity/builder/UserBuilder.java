package com.add.ad.entity.builder;

import com.add.ad.entity.User;
import com.google.gson.annotations.SerializedName;

public class UserBuilder {
    private String userType;
    private String userEmail;
    private String userPassword;
    private String nick;
    private String verifyCode;
    private String tag;

    public UserBuilder setUserType(String userType) {
        this.userType = userType;
        return this;
    }

    public UserBuilder setUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }

    public UserBuilder setUserPassword(String userPassword) {
        this.userPassword = userPassword;
        return this;
    }

    public UserBuilder setNick(String nick) {
        this.nick = nick;
        return this;
    }

    public UserBuilder setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
        return this;
    }

    public UserBuilder setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public User build(){
        return new User(userType, userEmail, userPassword, nick, verifyCode, tag);
    }
}
