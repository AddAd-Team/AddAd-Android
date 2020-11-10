package com.add.ad.data.repository.search;

import com.add.ad.entity.response.ResponseSearchInfo;

import java.util.ArrayList;

import io.reactivex.Single;
import retrofit2.Response;

public interface SearchRepository {
    Single<Response<ArrayList<ResponseSearchInfo>>> getCreators(int pageId);
}
