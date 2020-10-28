package com.add.ad.presentation.di.modules;


import android.content.Context;

import com.add.ad.data.local.SharedPref;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import dagger.hilt.android.qualifiers.ApplicationContext;
import io.reactivex.disposables.CompositeDisposable;

@Module
@InstallIn(ApplicationComponent.class)
public class AppModule {

    @Provides
    @Singleton
    public SharedPref provideSharedPref(@ApplicationContext Context context) { return new SharedPref(context); }

    @Provides
    public CompositeDisposable provideCompositeDisposable(){ return new CompositeDisposable();}
}
