package com.add.ad.presentation.di;

import android.content.Context;

import com.add.ad.data.api.Api;
import com.add.ad.data.local.SharedPref;
import com.add.ad.data.repository.AuthRepositoryImpl;
import com.add.ad.data.repository.AuthRepository;

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
            SharedPref sharedPref
    ) {
        return new AuthRepositoryImpl(api, sharedPref);
    }

    @Provides
    public SharedPref provideSharedPref(@ApplicationContext Context context) { return new SharedPref(context); }

}
