package com.add.ad.data.repository.feed;

import com.add.ad.entity.response.ResponseFeedInfo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import retrofit2.Response;

public interface FeedRepository {
    Single<Response<ArrayList<ResponseFeedInfo>>> getFeed();

    Single<Response<ResponseFeedInfo>> getDetailFeed(int postId);
}
