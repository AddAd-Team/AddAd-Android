package com.add.ad.presentation.entity;

public class AuthModel {
    private String userEmail;
    private String userPassword;

    public AuthModel(String userEmail, String userPassword){
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
