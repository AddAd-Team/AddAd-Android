package com.add.ad.data.repository.feed;

import com.add.ad.data.api.Api;
import com.add.ad.entity.response.ResponseFeedInfo;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Response;

public class FeedRepositoryImpl implements FeedRepository{
    Api api;

    @Inject
    public FeedRepositoryImpl(Api api){ this.api = api; }

    @Override
    public Single<Response<ResponseFeedInfo>> getFeed() {
        return api.getFeed();
    }
}
