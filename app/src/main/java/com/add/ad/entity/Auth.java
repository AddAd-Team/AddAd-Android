package com.add.ad.entity;

public class Auth {
    private String userEmail;
    private String userPassword;

    public Auth(String userEmail, String userPassword){
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
