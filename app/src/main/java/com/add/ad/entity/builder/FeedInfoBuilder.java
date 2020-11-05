package com.add.ad.entity.builder;

import com.add.ad.entity.response.ResponseFeedInfo;
import com.google.gson.annotations.SerializedName;

public class FeedInfoBuilder {
    private int feedId;
    private int userId;
    private String profileImage;
    private String feedImage;
    private String feedTitle;
    private String feedPrice;
    private String feedCloseRecruitment;
    private String feedCloseAd;
    private String feedTag;
    private String feedDescription;

    public void setFeedId(int feedId) {
        this.feedId = feedId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public void setFeedImage(String feedImage) {
        this.feedImage = feedImage;
    }

    public void setFeedTitle(String feedTitle) {
        this.feedTitle = feedTitle;
    }

    public void setFeedPrice(String feedPrice) {
        this.feedPrice = feedPrice;
    }

    public void setFeedCloseRecruitment(String feedCloseRecruitment) {
        this.feedCloseRecruitment = feedCloseRecruitment;
    }

    public void setFeedCloseAd(String feedCloseAd) {
        this.feedCloseAd = feedCloseAd;
    }

    public void setFeedTag(String feedTag) {
        this.feedTag = feedTag;
    }

    public void setFeedDescription(String feedDescription) {
        this.feedDescription = feedDescription;
    }

    public ResponseFeedInfo build(){
        return new ResponseFeedInfo(feedId, userId, profileImage, feedImage, feedTitle, feedPrice, feedCloseRecruitment, feedCloseAd, feedTag, feedDescription);
    }
}
