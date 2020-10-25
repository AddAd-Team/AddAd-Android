package com.add.ad.domain.repository;

import com.add.ad.domain.entity.Auth;
import com.add.ad.domain.entity.Token;

import io.reactivex.Single;
import retrofit2.Response;

public interface AuthRepository {
    Single<Response<Token>> signIn(Auth auth);
}