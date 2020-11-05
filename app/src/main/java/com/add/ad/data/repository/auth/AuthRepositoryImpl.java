package com.add.ad.data.repository.auth;

import com.add.ad.data.api.Api;
import com.add.ad.data.local.SharedPref;
import com.add.ad.data.repository.auth.AuthRepository;
import com.add.ad.entity.Auth;
import com.add.ad.entity.Token;
import com.add.ad.entity.User;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Response;

public class AuthRepositoryImpl implements AuthRepository {
    Api api;
    SharedPref sharedPref;

    @Inject
    public AuthRepositoryImpl(Api api, SharedPref sharedPref) {
        this.api = api;
        this.sharedPref = sharedPref;
    }

    @Override
    public Single<Response<Token>> signIn(Auth auth) {
        return api.signIn(auth);
    }

    @Override
    public Single<Response<Void>> signUp(User user) { return api.signUp(user); }

    @Override
    public Single<Response<Void>> requestVerifyCode(User user) { return api.requestVerifyCode(user); }

    @Override
    public Single<Response<Void>> sendVerifyCode(User user) { return api.sendVerifyCode(user); }
}
