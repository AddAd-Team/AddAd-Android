package com.add.ad.entity;

import java.util.ArrayList;

public class AccessData {
    private int postId;
    private ArrayList<Integer> idList;

    public AccessData(int postId, ArrayList<Integer> idList) {
        this.postId = postId;
        this.idList = idList;
    }
}