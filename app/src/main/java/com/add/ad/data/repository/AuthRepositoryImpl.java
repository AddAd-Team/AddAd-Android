package com.add.ad.data.repository;

import android.content.Context;

import com.add.ad.data.local.SharedPref;
import com.add.ad.data.mapper.AuthDataMapper;
import com.add.ad.data.mapper.TokenDataMapper;
import com.add.ad.data.remote.Api;
import com.add.ad.domain.entity.Auth;
import com.add.ad.domain.entity.Token;
import com.add.ad.domain.repository.AuthRepository;

import io.reactivex.Single;
import retrofit2.Response;

public class AuthRepositoryImpl implements AuthRepository {
    Api api;
    Context context;
    AuthDataMapper authDataMapper;
    TokenDataMapper tokenDataMapper;

    public AuthRepositoryImpl(Api api, AuthDataMapper authDataMapper, TokenDataMapper tokenDataMapper){
        this.api = api;
        this.context = context;
        this.authDataMapper = authDataMapper;
        this.tokenDataMapper = tokenDataMapper;
    }
    @Override
    public Single<Response<Token>> signIn(Auth auth) {
        return api.signIn(authDataMapper.mapFrom(auth));
    }
}
