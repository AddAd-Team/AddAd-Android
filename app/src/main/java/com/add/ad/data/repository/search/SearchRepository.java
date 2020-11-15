package com.add.ad.data.repository.search;

import com.add.ad.entity.response.ResponseSearchInfo;

import java.util.ArrayList;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Query;

public interface SearchRepository {
    Single<Response<ArrayList<ResponseSearchInfo>>> getCreators(int pageId);

    Single<Response<ArrayList<ResponseSearchInfo>>> searchCreator(int pageId, String searchName);

    Single<Response<ArrayList<ResponseSearchInfo>>> searchTag(int pageId, String searchTag);

    Single<Response<ResponseSearchInfo>> getDetailCreator(int userId);
}
