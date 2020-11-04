package com.add.ad.data.repository;

import com.add.ad.entity.Post;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import retrofit2.Response;

public interface WriteRepository {
    Single<Response<Void>> postWrite(MultipartBody.Part file, Post post);
}
