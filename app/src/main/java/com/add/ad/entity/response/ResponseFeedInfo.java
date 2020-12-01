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
    private String postTime;
    @SerializedName("deadline")
    private String deadline;
    @SerializedName("hashtag")
    private String feedTag;
    @SerializedName("description")
    private String feedDescription;
    @SerializedName("likes")
    private Boolean feedLikes;
    @SerializedName("application")
    private Boolean isApplied;
    @SerializedName("dateRemaining")
    private String feedRemainDate;
    @SerializedName("recruitmentClosing")
    private String feedCloseRecruitmentDate;
    @SerializedName("adClosing")
    private String feedCloseAdDate;

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

    public String getPostTime() {
        return postTime.substring(4,6) + "월 " + postTime.substring(6,8) + "일";
    }

    public String getDeadline() {
        return deadline.substring(4,6) + "월 " + deadline.substring(6,8) + "일";
    }

    public String getFeedTag() {
        return feedTag;
    }

    public String getFeedDescription() {
        return feedDescription;
    }

    public int getFeedRemainDate() {
        return Integer.parseInt(feedRemainDate);
    }

    public int getFeedCloseRecruitmentDate() {
        return Integer.parseInt(feedCloseRecruitmentDate);
    }

    public int getFeedCloseAdDate() {
        return Integer.parseInt(feedCloseAdDate);
    }
}
