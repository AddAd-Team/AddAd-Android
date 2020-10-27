package com.add.ad.presentation.di;

import android.content.Context;

import com.add.ad.data.api.Api;
import com.add.ad.data.datasource.AuthDataSource;
import com.add.ad.data.local.SharedPref;
import com.add.ad.data.mapper.AuthDataMapper;
import com.add.ad.data.mapper.TokenDataMapper;
import com.add.ad.data.repository.AuthRepositoryImpl;
import com.add.ad.domain.repository.AuthRepository;
import com.add.ad.domain.usecase.LoginUseCase;
import com.add.ad.presentation.mapper.AuthModelMapper;
import com.add.ad.presentation.mapper.TokenModelMapper;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import dagger.hilt.android.qualifiers.ApplicationContext;

@Module
@InstallIn(ApplicationComponent.class)
public class LoginModule {

    @Provides
    public AuthRepository provideAuthRepo(
            Api api,
            SharedPref sharedPref,
            AuthDataMapper authDataMapper,
            TokenDataMapper tokenDataMapper
    ) {
        return new AuthRepositoryImpl(api, sharedPref, authDataMapper, tokenDataMapper);
    }

    @Provides
    public LoginUseCase provideLoginUseCase(AuthRepository authRepository) {
        return new LoginUseCase(authRepository);
    }

    @Provides
    public AuthDataMapper provideAuthMapper() {
        return new AuthDataMapper();
    }

    @Provides
    public TokenDataMapper provideTokenMapper() {
        return new TokenDataMapper();
    }

    @Provides
    public AuthModelMapper provideAuthModelMapper() {
        return new AuthModelMapper();
    }

    @Provides
    public TokenModelMapper provideTokenModelMapper() {
        return new TokenModelMapper();
    }

    @Provides
    public SharedPref provideSharedPref(@ApplicationContext Context context) { return new SharedPref(context); }

//    @Provides
//    public AuthDataSource provideAuthDataSource(Api api){ return new AuthDataSource(api); }
}
