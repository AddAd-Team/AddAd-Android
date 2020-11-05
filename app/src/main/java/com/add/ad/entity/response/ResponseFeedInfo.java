package com.add.ad.entity.response;

import com.google.gson.annotations.SerializedName;

public class ResponseFeedInfo {
    @SerializedName("id")
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
    @SerializedName("deadline")
    private String feedCloseRecruitment;
    @SerializedName("addline")
    private String feedCloseAd;
    @SerializedName("hashtag")
    private String feedTag;
    @SerializedName("description")
    private String feedDescription;

    public ResponseFeedInfo(int feedId, int userId, String profileImage, String feedImage, String feedTitle, String feedPrice, String feedCloseRecruitment, String feedCloseAd, String feedTag, String feedDescription) {
        this.feedId = feedId;
        this.userId = userId;
        this.profileImage = profileImage;
        this.feedImage = feedImage;
        this.feedTitle = feedTitle;
        this.feedPrice = feedPrice;
        this.feedCloseRecruitment = feedCloseRecruitment;
        this.feedCloseAd = feedCloseAd;
        this.feedTag = feedTag;
        this.feedDescription = feedDescription;
    }
}
