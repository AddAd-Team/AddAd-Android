package com.add.ad.data.repository;

import com.add.ad.entity.Auth;
import com.add.ad.entity.Token;

import io.reactivex.Single;
import retrofit2.Response;

public interface AuthRepository {
    Single<Response<Token>> signIn(Auth auth);
}