package com.add.ad.entity.response;

import com.google.gson.annotations.SerializedName;

public class ResponseFeedInfo {
    @SerializedName("postId")
    private int feedId;
    @SerializedName("userId")
    private int userId;
    @SerializedName("profileImg")
    private String profileImage;
    @SerializedName("postImg")
    private String feedImage;
    @SerializedName("title")
    private String feedTitle;
    @SerializedName("price")
    private String feedPrice;
    @SerializedName("postTime")
    private String feedCloseRecruitment;
    @SerializedName("deadline")
    private String feedCloseAd;
    @SerializedName("hashtag")
    private String feedTag;
    @SerializedName("description")
    private String feedDescription;
    @SerializedName("likes")
    private Boolean feedLikes;
    @SerializedName("application")
    private Boolean isApplied;

    public Boolean getFeedLikes() {
        return feedLikes;
    }

    public Boolean getApplied() {
        return isApplied;
    }

    public int getFeedId() {
        return feedId;
    }

    public int getUserId() {
        return userId;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String getFeedImage() {
        return feedImage;
    }

    public String getFeedTitle() {
        return feedTitle;
    }

    public String getFeedPrice() {
        return feedPrice;
    }

    public String getFeedCloseRecruitment() {
        return feedCloseRecruitment.substring(4,6) + "월 " + feedCloseRecruitment.substring(6,8) + "일";
    }

    public String getFeedCloseAd() {
        return feedCloseAd.substring(4,6) + "월 " + feedCloseAd.substring(6,8) + "일";
    }

    public String getFeedTag() {
        return feedTag;
    }

    public String getFeedDescription() {
        return feedDescription;
    }
}
