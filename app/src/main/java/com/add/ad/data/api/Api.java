package com.add.ad.data.api;

import com.add.ad.entity.Auth;
import com.add.ad.entity.Token;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Api {
    @POST("/api/auth/signin")
    Single<Response<Token>> signIn(@Body Auth authData);
}
