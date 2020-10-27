package com.add.ad.data.repository;

import com.add.ad.data.api.Api;
import com.add.ad.data.datasource.AuthDataSource;
import com.add.ad.data.local.SharedPref;
import com.add.ad.data.mapper.AuthDataMapper;
import com.add.ad.data.mapper.TokenDataMapper;
import com.add.ad.domain.entity.Auth;
import com.add.ad.domain.entity.Token;
import com.add.ad.domain.repository.AuthRepository;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Response;

public class AuthRepositoryImpl implements AuthRepository {
    Api api;
    SharedPref sharedPref;
    AuthDataMapper authDataMapper;
    TokenDataMapper tokenDataMapper;

    @Inject
    public AuthRepositoryImpl(Api api, SharedPref sharedPref, AuthDataMapper authDataMapper, TokenDataMapper tokenDataMapper) {
        this.api = api;
        this.sharedPref = sharedPref;
        this.authDataMapper = authDataMapper;
        this.tokenDataMapper = tokenDataMapper;
    }

    @Override
    public Single<Response<Token>> signIn(Auth auth) {
        return api.signIn(authDataMapper.mapFrom(auth));
    }
}
