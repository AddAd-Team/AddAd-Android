package com.add.ad.entity.response;

import com.google.gson.annotations.SerializedName;

public class ResponseUserInfo {
    @SerializedName("profileImg")
    private String userImage;
    @SerializedName("name")
    private  String userName;
    @SerializedName("hashtag")
    private  String userTag;
    @SerializedName("description")
    private  String userDescription;

    public String getUserImage() {
        return userImage;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserTag() {
        return userTag;
    }

    public String getUserDescription() {
        return userDescription;
    }
}
