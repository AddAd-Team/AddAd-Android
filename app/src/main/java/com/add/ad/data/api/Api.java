package com.add.ad.data.api;

import com.add.ad.entity.Auth;
import com.add.ad.entity.response.ResponseFeedInfo;
import com.add.ad.entity.response.ResponseSearchInfo;
import com.add.ad.entity.response.ResponseUserInfo;
import com.add.ad.entity.Token;
import com.add.ad.entity.User;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

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
    Single<Response<Void>> changePassword(@Query("password") String password);

    @GET("/api/mypage/profile")
    Single<Response<ResponseUserInfo>> getUserProfile();

    @GET("/api/post/feed")
    Single<Response<ArrayList<ResponseFeedInfo>>> getFeed();

    @Multipart
    @PUT("/api/mypage/modifyProfile")
    Single<Response<Void>> editProfile(@Part MultipartBody.Part file,
                                       @Part("name") RequestBody profileName,
                                       @Part("description") RequestBody profileDescription,
                                       @Part("hashtag") RequestBody profileTag);

    @GET("api/post/feed/{postId}")
    Single<Response<ResponseFeedInfo>> getDetailFeed(@Path("postId") int postId);

    @GET("api/search/basic/")
    Single<Response<ArrayList<ResponseSearchInfo>>> getCreator(@Query("page") int pageId);

    @GET("api/search/name")
    Single<Response<ArrayList<ResponseSearchInfo>>> searchCreator(@Query("page") int pageId,
                                                       @Query("name") String searchName);

    @GET("api/search/tag")
    Single<Response<ArrayList<ResponseSearchInfo>>> searchTag(@Query("page") int pageId,
                                                              @Query("tag") String searchTag);

    @GET("/api/search/user")
    Single<Response<ResponseSearchInfo>> getDetailCreator(@Query("id") int userId);

    @POST("/api/like/{postId}")
    Single<Response<Void>> postLike(@Path("postId") int postId);

    @DELETE("/api/like/{postId}")
    Single<Response<Void>> deleteLike(@Path("postId") int postId);

    @POST("/api/application/apply/{postId}")
    Single<Response<Void>> postApply(@Path("postId") int postId);
}
