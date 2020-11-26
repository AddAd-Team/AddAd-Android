package com.add.ad.data.repository.mypage;

import androidx.lifecycle.MutableLiveData;

import com.add.ad.data.api.Api;
import com.add.ad.entity.response.ResponseApplyInfo;
import com.add.ad.entity.response.ResponseMyAdInfo;
import com.add.ad.entity.response.ResponseUserInfo;

import java.util.ArrayList;

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

    @Override
    public Single<Response<ArrayList<ResponseMyAdInfo>>> getLikeAdList() {
        return api.getLikeAdList();
    }

    @Override
    public Single<Response<ArrayList<ResponseMyAdInfo>>> getMyAdList() {
        return api.getMyAdList();
    }

    @Override
    public Single<Response<ArrayList<ResponseApplyInfo>>> getAppliedList(int postId) {
        return api.getAppliedList(postId);
    }

}
