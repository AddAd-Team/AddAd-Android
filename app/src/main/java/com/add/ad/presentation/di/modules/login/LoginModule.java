package com.add.ad.presentation.di.modules.login;

import android.content.Context;

import com.add.ad.data.api.Api;
import com.add.ad.data.local.SharedPref;
import com.add.ad.data.repository.AuthRepositoryImpl;
import com.add.ad.data.repository.AuthRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import dagger.hilt.android.qualifiers.ApplicationContext;
import io.reactivex.disposables.CompositeDisposable;

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
    @Singleton
    public SharedPref provideSharedPref(@ApplicationContext Context context) { return new SharedPref(context); }

    @Provides
    public CompositeDisposable provideCompositeDisposable(){ return new CompositeDisposable();}

}
