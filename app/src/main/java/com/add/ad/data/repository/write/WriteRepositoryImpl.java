package com.add.ad.data.repository.write;

import com.add.ad.data.api.Api;
import com.add.ad.data.repository.write.WriteRepository;

import javax.inject.Inject;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;

public class WriteRepositoryImpl implements WriteRepository {
    Api api;

    @Inject
    public WriteRepositoryImpl(Api api){
        this.api = api;
    }

    @Override
    public Single<Response<Void>> postWrite(MultipartBody.Part file, RequestBody postTitle, RequestBody postTag, RequestBody postContent, RequestBody postPrice, RequestBody postEndDate, RequestBody adEndDate) {
        return api.postWrite(file, postTitle, postTag, postContent, postPrice, postEndDate, adEndDate);
    }
}
