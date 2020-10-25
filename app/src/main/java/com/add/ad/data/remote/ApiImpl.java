package com.add.ad.data.remote;

import com.add.ad.data.api.AuthApi;
import com.add.ad.data.entity.AuthData;
import com.add.ad.domain.entity.Token;

import io.reactivex.Single;
import retrofit2.Response;

public class ApiImpl implements Api{
    private AuthApi userApi;

    public ApiImpl(AuthApi userApi){
        this.userApi = userApi;
    }

    @Override
    public Single<Response<Token>> signIn(AuthData userData) {
        return userApi.signIn(userData);
    }
}
