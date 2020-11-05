package com.add.ad.data.repository.feed;

import com.add.ad.entity.response.ResponseFeedInfo;

import io.reactivex.Single;
import retrofit2.Response;

public interface FeedRepository {
    Single<Response<ResponseFeedInfo>> getFeed();
}
