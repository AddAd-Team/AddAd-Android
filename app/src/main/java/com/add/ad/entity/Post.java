package com.add.ad.entity;

import com.google.gson.annotations.SerializedName;

public class Post {
    @SerializedName("title")
    private String postTitle;
    @SerializedName("hasgtag")
    private String postTag;
    @SerializedName("description")
    private String postDescription;
    @SerializedName("postTime")
    private String postEndTime;
    @SerializedName("deadline")
    private String adEndTime;


    public Post(String postTitle, String postTag, String postDescription, String postEndTime, String adEndTime) {
        this.postTitle = postTitle;
        this.postTag = postTag;
        this.postDescription = postDescription;
        this.postEndTime = postEndTime;
        this.adEndTime = adEndTime;
    }
}
