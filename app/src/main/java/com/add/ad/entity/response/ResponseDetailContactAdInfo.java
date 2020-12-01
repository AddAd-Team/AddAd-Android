package com.add.ad.entity.response;

import com.google.gson.annotations.SerializedName;

public class ResponseDetailContactAdInfo {
    private int postId;
    private String title;
    private String postImg;
    private String postTime;
    private String recruitmentClosing;
    @SerializedName("hashtag")
    private String hashTag;

    public int getUserId() {
        return postId;
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

    public int getPostId() {
        return postId;
    }
}
