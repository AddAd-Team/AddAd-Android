package com.add.ad.presentation.di;

import com.add.ad.data.remote.Api;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(ApplicationComponent.class)
public class NetworkModule {

    @Provides
    @Singleton
    public static Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("http://10.156.145.143")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public static Api provideApiService(Retrofit retrofit) {
        return retrofit.create(Api.class);
    }
}