package com.add.ad.data.remote;

import com.add.ad.data.entity.AuthData;
import com.add.ad.domain.entity.Token;

import io.reactivex.Single;
import retrofit2.Response;

public interface Api {
    Single<Response<Token>> signIn(AuthData user);
}
