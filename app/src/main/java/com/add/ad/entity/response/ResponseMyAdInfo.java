package com.add.ad.entity.response;

import com.google.gson.annotations.SerializedName;

public class ResponseMyAdInfo {
    private int userId;
    private String title;
    private String postImg;
    private String postTime;
    private String recruitmentClosing;
    @SerializedName("hashtag")
    private String hashTag;
    @SerializedName("userinfo")
    private String userInfo;

    public int getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getPostImg() {
        return postImg;
    }

    public String getPostTime() {
        return postTime.substring(4,6) + "월 " + postTime.substring(6,8) + "일";
    }

    public int getRecruitmentClosing() {
        return Integer.parseInt(recruitmentClosing);
    }

    public String getHashTag() {
        return hashTag;
    }

    public String getUserInfo() {
        return userInfo;
    }
}
