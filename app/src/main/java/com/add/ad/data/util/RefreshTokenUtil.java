package com.add.ad.data.util;

import android.util.Log;

import com.add.ad.data.api.Api;
import com.add.ad.data.local.SharedPref;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class RefreshTokenUtil {
    Api api;
    SharedPref sharedPref;
    CompositeDisposable compositeDisposable;

    @Inject
    public RefreshTokenUtil(Api api, SharedPref sharedPref, CompositeDisposable compositeDisposable) {
        this.api = api;
        this.sharedPref = sharedPref;
        this.compositeDisposable = compositeDisposable;
    }

    public void getToken() {
        compositeDisposable.add(api.getToken(sharedPref.getToken(false))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(it -> {
                    sharedPref.saveToken(it.body().getAccessToken(),true);
                    sharedPref.saveToken(it.body().getRefreshToken(),false);
                }, it -> {
                    Log.e("dasfasdf", "dsafasdfa");
                }));
    }
}
