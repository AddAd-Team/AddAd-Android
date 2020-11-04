package com.add.ad.presentation.di.modules.auth;

import com.add.ad.data.api.Api;
import com.add.ad.data.local.SharedPref;
import com.add.ad.data.repository.auth.AuthRepository;
import com.add.ad.data.repository.auth.AuthRepositoryImpl;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class AuthModule {

    @Provides
    public AuthRepository provideAuthRepo(
            Api api,
            SharedPref sharedPref
    ) {
        return new AuthRepositoryImpl(api, sharedPref);
    }

}
