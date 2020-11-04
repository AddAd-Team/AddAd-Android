package com.add.ad.data.repository.auth;

import com.add.ad.entity.Auth;
import com.add.ad.entity.Token;
import com.add.ad.entity.User;

import io.reactivex.Single;
import retrofit2.Response;

public interface AuthRepository {
    Single<Response<Token>> signIn(Auth auth);

    Single<Response<Void>> signUp(User user);

    Single<Response<Void>> requestVerifyCode(User user);

    Single<Response<Void>> sendVerifyCode(User user);
}