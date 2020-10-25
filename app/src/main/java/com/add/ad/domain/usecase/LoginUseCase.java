package com.add.ad.domain.usecase;

import com.add.ad.domain.base.UseCase;
import com.add.ad.domain.entity.Auth;
import com.add.ad.domain.entity.Token;
import com.add.ad.domain.repository.AuthRepository;

import io.reactivex.Single;
import retrofit2.Response;

public class LoginUseCase extends UseCase<Auth, Response<Token>> {
    AuthRepository authRepository;

    @Override
    protected Single<Response<Token>> create(Auth data) {
        return authRepository.signIn(data);
    }
}
