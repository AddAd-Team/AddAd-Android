package com.add.ad.data.api;

import com.add.ad.entity.Auth;
import com.add.ad.entity.Post;
import com.add.ad.entity.Token;
import com.add.ad.entity.User;

import io.reactivex.Completable;
import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface Api {
    @POST("/api/auth/signin")
    Single<Response<Token>> signIn(@Body Auth authData);

    @POST("/api/user/signup")
    Single<Response<Void>> signUp(@Body User userData);

    @POST("/api/user/emailSender")
    Single<Response<Void>> requestVerifyCode(@Body User userData);

    @PUT("/api/user/emailAuth")
    Single<Response<Void>> sendVerifyCode(@Body User userData);

    @Multipart
    @POST("/api/post/write")
    Single<Response<Void>> postWrite(@Part MultipartBody.Part file,
                                     @Part("title") RequestBody postTitle,
                                     @Part("hashtag") RequestBody postTag,
                                     @Part("description") RequestBody postContent,
                                     @Part("price") RequestBody postPrice,
                                     @Part("postTime") RequestBody postEndDate,
                                     @Part("deadline") RequestBody adEndDate);

    @PUT("/api/mypage/passwordChange")
    Single<Response<Void>> changePassword(@Body String password);
}
