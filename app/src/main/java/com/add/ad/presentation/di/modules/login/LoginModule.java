package com.add.ad.presentation.di.modules.login;

import com.add.ad.data.api.Api;
import com.add.ad.data.local.SharedPref;
import com.add.ad.data.repository.AuthRepository;
import com.add.ad.data.repository.AuthRepositoryImpl;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import dagger.hilt.android.components.FragmentComponent;
import dagger.hilt.android.scopes.FragmentScoped;
import io.reactivex.disposables.CompositeDisposable;

@Module
@InstallIn(FragmentComponent.class)
public class LoginModule {

    @Provides
    public AuthRepository provideAuthRepo(
            Api api,
            SharedPref sharedPref
    ) {
        return new AuthRepositoryImpl(api, sharedPref);
    }

}
