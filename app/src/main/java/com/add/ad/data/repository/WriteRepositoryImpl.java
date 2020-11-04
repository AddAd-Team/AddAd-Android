package com.add.ad.data.repository;

import com.add.ad.data.api.Api;
import com.add.ad.entity.Post;

import javax.inject.Inject;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import retrofit2.Response;

public class WriteRepositoryImpl implements WriteRepository{
    Api api;

    @Inject
    public WriteRepositoryImpl(Api api){
        this.api = api;
    }

    @Override
    public Single<Response<Void>> postWrite(MultipartBody.Part file, Post post) {
        return api.postWrite(file, post);
    }
}
