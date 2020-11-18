package com.add.ad.presentation.viewModel.mypage.myad;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;

import com.add.ad.data.local.SharedPref;
import com.add.ad.data.repository.mypage.MyPageRepository;
import com.add.ad.entity.response.ResponseMyAdInfo;
import com.add.ad.presentation.base.BaseViewModel;
import com.add.ad.presentation.base.SingleLiveEvent;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MyAdViewModel extends BaseViewModel {
    CompositeDisposable compositeDisposable;
    MyPageRepository myPageRepository;
    SharedPref sharedPref;

    public MutableLiveData<Boolean> userInfo = new MutableLiveData<>(true);
    public MutableLiveData<ArrayList<ResponseMyAdInfo>> creatorAdList = new MutableLiveData<>();
    public MutableLiveData<ArrayList<ResponseMyAdInfo>> advertiserAdList = new MutableLiveData<>();

    public SingleLiveEvent<Void> creatorAdEvent = new SingleLiveEvent<>();
    public SingleLiveEvent<Void> advertiserAdEvent = new SingleLiveEvent<>();

    @ViewModelInject
    public MyAdViewModel(CompositeDisposable compositeDisposable, MyPageRepository myPageRepository, SharedPref sharedPref) {
        this.compositeDisposable = compositeDisposable;
        this.myPageRepository = myPageRepository;
        this.sharedPref = sharedPref;
    }

    public void getMyAdList() {
        userInfo.setValue(getUserInfo());

        compositeDisposable.add(myPageRepository.getMyAdList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(it -> {
                    if (it.code() == 200) {
                        if (getUserInfo()) {
                            creatorAdList.setValue(it.body());
                            creatorAdEvent.call();
                        } else {
                            advertiserAdList.setValue(it.body());
                            advertiserAdEvent.call();
                        }
                    }
                }, it -> {
                    Log.e("error", it.getMessage());
                }));
    }

    private Boolean getUserInfo() {
        if (sharedPref.getInfo(false).equals("creator")) {
            return true;
        } else if (sharedPref.getInfo(false).equals("advertiser")) {
            return false;
        }
        return false;
    }
}
