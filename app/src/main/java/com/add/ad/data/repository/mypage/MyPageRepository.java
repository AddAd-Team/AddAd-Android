package com.add.ad.data.repository.mypage;

import com.add.ad.entity.AccessData;
import com.add.ad.entity.response.ResponseApplyInfo;
import com.add.ad.entity.response.ResponseMyAdInfo;
import com.add.ad.entity.response.ResponseUserInfo;

import java.util.ArrayList;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.http.Body;

public interface MyPageRepository {
    Single<Response<Void>> changePassword(String password);

    Single<Response<ResponseUserInfo>> getUserProfile();

    Single<Response<Void>> editProfile(MultipartBody.Part file, RequestBody profileName, RequestBody profileDescription, RequestBody profileTag);

    Single<Response<ArrayList<ResponseMyAdInfo>>> getLikeAdList();

    Single<Response<ArrayList<ResponseMyAdInfo>>> getMyAdList();

    Single<Response<ArrayList<ResponseApplyInfo>>> getAppliedList(int postId);

    Single<Response<Void>> postAppliedList(AccessData accessData);
}
