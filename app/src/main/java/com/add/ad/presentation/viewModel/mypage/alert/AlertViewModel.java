package com.add.ad.presentation.viewModel.mypage.alert;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;

import com.add.ad.data.local.SharedPref;
import com.add.ad.data.repository.mypage.MyPageRepository;
import com.add.ad.entity.response.ResponseAlertInfo;
import com.add.ad.presentation.base.BaseViewModel;
import com.add.ad.presentation.base.SingleLiveEvent;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class AlertViewModel extends BaseViewModel {
    CompositeDisposable compositeDisposable;
    MyPageRepository myPageRepository;
    SharedPref sharedPref;

    public MutableLiveData<ArrayList<ResponseAlertInfo>> alertList = new MutableLiveData<>();

    public SingleLiveEvent<Void> alertEvent = new SingleLiveEvent<>();

    @ViewModelInject
    public AlertViewModel(CompositeDisposable compositeDisposable, MyPageRepository myPageRepository, SharedPref sharedPref) {
        this.compositeDisposable = compositeDisposable;
        this.myPageRepository = myPageRepository;
        this.sharedPref = sharedPref;
    }

    public Boolean getUserInfo() {
        if (sharedPref.getInfo().equals("creator")) {
            return true;
        } else if (sharedPref.getInfo().equals("advertiser")) {
            return false;
        }
        return false;
    }

    public void getNotificationList() {
        compositeDisposable.add(myPageRepository.getNotificationList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(it -> {
                    if (it.code() == 200) {
                        alertList.setValue(it.body());
                        alertEvent.call();
                    }
                }, it -> {
                    Log.e("error",it.getMessage());
                }));
    }

    public void clickBack() {
        backEvent.call();
    }
}
