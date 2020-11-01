package com.add.ad.data.api;

import android.content.SharedPreferences;

import com.add.ad.data.local.SharedPref;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthorizationInterceptor implements Interceptor {
    SharedPref pref;

    @Inject
    public AuthorizationInterceptor(SharedPref pref){
        this.pref = pref;
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request = chain.request()
                .newBuilder()
                .addHeader("Authorization",pref.getToken(true))
                .build();

        return chain.proceed(request);
    }
}
