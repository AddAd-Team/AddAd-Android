package com.add.ad.data.repository;

import com.add.ad.entity.Post;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.http.Part;

public interface WriteRepository {
    Single<Response<Void>> postWrite(MultipartBody.Part file, RequestBody postTitle, RequestBody postTag, RequestBody postContent, RequestBody postPrice, RequestBody postEndDate, RequestBody adEndDate);
}
