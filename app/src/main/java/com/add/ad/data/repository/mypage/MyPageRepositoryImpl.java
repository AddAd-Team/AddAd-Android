package com.add.ad.data.repository.mypage;

import com.add.ad.data.api.Api;

import javax.inject.Inject;

import io.reactivex.Single;
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
}
