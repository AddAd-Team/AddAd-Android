package com.add.ad.data.repository.mypage;

import com.add.ad.data.api.Api;
import com.add.ad.entity.response.ResponseUserInfo;

import javax.inject.Inject;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;

public class MyPageRepositoryImpl implements MyPageRepository{
    Api api;

    @Inject
    public MyPageRepositoryImpl(Api api) {
        this.api = api;
    }

    @Override
    public Single<Response<Void>> changePassword(String password) {
        return api.changePassword(password);
    }

    @Override
    public Single<Response<ResponseUserInfo>> getUserProfile() {
        return api.getUserProfile();
    }

    @Override
    public Single<Response<Void>> editProfile(MultipartBody.Part file, RequestBody profileName, RequestBody profileDescription, RequestBody profileTag) {
        return api.editProfile(file, profileName, profileDescription, profileTag);
    }

}
