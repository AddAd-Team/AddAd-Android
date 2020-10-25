package com.add.ad.presentation.di;

import com.add.ad.data.api.AuthApi;
import com.add.ad.data.mapper.AuthDataMapper;
import com.add.ad.data.mapper.TokenDataMapper;
import com.add.ad.data.remote.Api;
import com.add.ad.data.remote.ApiImpl;
import com.add.ad.data.repository.AuthRepositoryImpl;
import com.add.ad.domain.repository.AuthRepository;
import com.add.ad.domain.usecase.LoginUseCase;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityRetainedComponent;
import dagger.hilt.android.scopes.FragmentScoped;

@Module
@InstallIn(ActivityRetainedComponent.class)
public class LoginModule {

    @Provides
    @FragmentScoped
    public static AuthRepository provideAuthRepo(
            Api api,
            AuthDataMapper authDataMapper,
            TokenDataMapper tokenDataMapper
    ){
        return new AuthRepositoryImpl(api, authDataMapper, tokenDataMapper);
    }

    @Provides
    @FragmentScoped
    public static LoginUseCase provideLoginUseCase(
            AuthRepository authRepository
    ){
        return new LoginUseCase(authRepository);
    }

    @Provides
    @FragmentScoped
    public static Api provideApi(
            AuthApi authApi
    ){
        return new ApiImpl(authApi);
    }

    @Provides
    @FragmentScoped
    public static AuthDataMapper provideAuthMapper(){
        return new AuthDataMapper();
    }

    @Provides
    @FragmentScoped
    public static TokenDataMapper provideTokenMapper(){
        return new TokenDataMapper();
    }
}
