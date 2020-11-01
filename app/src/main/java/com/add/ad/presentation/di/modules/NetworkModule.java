package com.add.ad.presentation.di.modules;

import com.add.ad.data.api.Api;
import com.add.ad.data.api.AuthorizationInterceptor;
import com.add.ad.data.local.SharedPref;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(ApplicationComponent.class)
public class NetworkModule {

    @Provides
    @Singleton
    public static HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Singleton
    @Provides
    public static AuthorizationInterceptor provideInterceptor(SharedPref pref) {
        return new AuthorizationInterceptor(pref);
    }

    @Singleton
    @Provides
    public static Api provideApiService(AuthorizationInterceptor authorizationInterceptor, HttpLoggingInterceptor httpLoggingInterceptor) {
        return new Retrofit.Builder()
                .baseUrl("http://192.168.137.185:8080")
                .client(new OkHttpClient.Builder()
                        .addInterceptor(authorizationInterceptor)
                        .addInterceptor(httpLoggingInterceptor)
                        .build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Api.class);
    }
}