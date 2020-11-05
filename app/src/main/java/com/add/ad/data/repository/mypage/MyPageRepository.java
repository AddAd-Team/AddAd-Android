package com.add.ad.data.repository.mypage;

import com.add.ad.entity.ResponseUserInfo;

import io.reactivex.Single;
import retrofit2.Response;

public interface MyPageRepository {
    Single<Response<Void>> changePassword(String password);

    Single<Response<ResponseUserInfo>> getUserProfile();
}
