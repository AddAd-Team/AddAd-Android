package com.add.ad.data.api;

import com.add.ad.data.entity.AuthData;
import com.add.ad.domain.entity.Token;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.POST;

public interface AuthApi {
    @POST("/api/auth/signin")
    Single<Response<Token>> signIn(AuthData userData);
}
