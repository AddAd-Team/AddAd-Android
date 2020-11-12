package com.add.ad.entity.response;

import com.google.gson.annotations.SerializedName;

public class ResponseSearchInfo {
    @SerializedName("id")
    private int userId;
    @SerializedName("profileImg")
    private String profileImage;
    @SerializedName("name")
    private String userName;
    @SerializedName("hashtag")
    private String userTag;

    public ResponseSearchInfo(int userId) {
        this.userId = userId;
    }
    
    public String getProfileImage() {
        return profileImage;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserTag() {
        return userTag;
    }
}
