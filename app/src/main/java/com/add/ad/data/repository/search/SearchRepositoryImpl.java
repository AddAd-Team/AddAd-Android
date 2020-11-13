package com.add.ad.data.repository.search;

import com.add.ad.data.api.Api;
import com.add.ad.entity.response.ResponseSearchInfo;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Response;

public class SearchRepositoryImpl implements SearchRepository{
    Api api;

    @Inject
    public SearchRepositoryImpl(Api api) {
        this.api = api;
    }

    @Override
    public Single<Response<ArrayList<ResponseSearchInfo>>> getCreators(int pageId) {
        return api.getCreator(pageId);
    }

    @Override
    public Single<Response<ArrayList<ResponseSearchInfo>>> searchCreator(int pageId, String searchName) {
        return api.searchCreator(pageId, searchName);
    }

    @Override
    public Single<Response<ArrayList<ResponseSearchInfo>>> searchTag(int pageId, String searchTag) {
        return api.searchTag(pageId, searchTag);
    }

    @Override
    public Single<Response<ResponseSearchInfo>> getDetailCreator(int userId) {
        return api.getDetailCreator(userId);
    }
}
