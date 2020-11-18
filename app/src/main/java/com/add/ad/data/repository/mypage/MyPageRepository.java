package com.add.ad.data.repository.mypage;

import com.add.ad.entity.response.ResponseMyAdInfo;
import com.add.ad.entity.response.ResponseUserInfo;

import java.util.ArrayList;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;

public interface MyPageRepository {
    Single<Response<Void>> changePassword(String password);

    Single<Response<ResponseUserInfo>> getUserProfile();

    Single<Response<Void>> editProfile(MultipartBody.Part file, RequestBody profileName, RequestBody profileDescription, RequestBody profileTag);

    Single<Response<ArrayList<ResponseMyAdInfo>>> getLikeAd();
}
