package com.add.ad.entity;

import java.util.ArrayList;

public class AccessData {
    private int postId;
    private ArrayList<UserListData> userList;

    public AccessData(int postId, ArrayList<UserListData> userList) {
        this.postId = postId;
        this.userList = userList;
    }
}