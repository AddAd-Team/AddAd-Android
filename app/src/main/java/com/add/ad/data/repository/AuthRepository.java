package com.add.ad.data.repository;

import com.add.ad.entity.Auth;
import com.add.ad.entity.Token;
import com.add.ad.entity.User;

import io.reactivex.Single;
import retrofit2.Response;

public interface AuthRepository {
    Single<Response<Token>> signIn(Auth auth);

    Single<Response<Void>> signUp(User user);

    Single<Response<Void>> requestVerifyCode(String userEmail);

    Single<Response<Void>> sendVerifyCode(User user);
}