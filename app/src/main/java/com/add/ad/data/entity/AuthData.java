package com.add.ad.data.entity;

import com.google.gson.annotations.SerializedName;

public class AuthData {
    @SerializedName("email")
    private String userEmail;
    @SerializedName("password")
    private String userPassword;

    public AuthData(String userEmail, String userPassword) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }


    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
