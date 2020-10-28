package com.add.ad.data.api;

import com.add.ad.entity.Auth;
import com.add.ad.entity.Token;
import com.add.ad.entity.User;

import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Api {
    @POST("/api/auth/signin")
    Single<Response<Token>> signIn(@Body Auth authData);

    @POST("/api/auth/signup")
    Single<Response<Void>> signUp(@Body User userData);

    @POST("api/auth/signup/email")
    Single<Response<Void>> requestVerifyCode(@Body String userEmail);
}
