package com.add.ad.presentation.viewModel.mypage;

import android.util.Log;

import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

import com.add.ad.data.local.SharedPref;
import com.add.ad.data.repository.mypage.MyPageRepository;
import com.add.ad.entity.ResponseUserInfo;
import com.add.ad.presentation.base.BaseViewModel;
import com.add.ad.presentation.base.SingleLiveEvent;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MyPageViewModel extends BaseViewModel {
    MyPageRepository myPageRepository;
    CompositeDisposable compositeDisposable;
    SharedPref sharedPref;

    @ViewModelInject
    public MyPageViewModel(MyPageRepository myPageRepository, SharedPref sharedPref, CompositeDisposable compositeDisposable, @Assisted SavedStateHandle savedStateHandle) {
        this.myPageRepository = myPageRepository;
        this.compositeDisposable = compositeDisposable;
        this.sharedPref = sharedPref;
    }


    public MutableLiveData<String> profileName = new MutableLiveData<>();
    public MutableLiveData<String> profileTag = new MutableLiveData<>();
    public MutableLiveData<String> profileImageUrl = new MutableLiveData<>();

    public SingleLiveEvent<String> profileImageEvent = new SingleLiveEvent<>();
    public SingleLiveEvent<Void> logoutEvent = new SingleLiveEvent<>();
    public SingleLiveEvent<Void> myAdEvent = new SingleLiveEvent<>();
    public SingleLiveEvent<Void> myProfileEvent = new SingleLiveEvent<>();
    public SingleLiveEvent<Void> changePwEvent = new SingleLiveEvent<>();
    public SingleLiveEvent<Void> confirmLogoutEvent = new SingleLiveEvent<>();
    public SingleLiveEvent<Void> cancelLogoutEvent = new SingleLiveEvent<>();

    public void getProfile() {

        compositeDisposable.add(myPageRepository.getUserProfile()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(it -> {
                    Log.d("url", it.body() != null ? it.body().getUserImage() : null);

                    if (it.code() == 200) {
                        profileImageUrl.setValue(it.body() != null ? it.body().getUserImage() : null);
                        profileName.setValue(it.body() != null ? it.body().getUserName() : null);
                        profileTag.setValue(it.body() != null ? it.body().getUserTag() : null);
                    }
                }, it -> createToastEvent.setValue("알 수 없는 오류가 발생하였습니다.")));

    }

    public void clickConfirm() {
        sharedPref.removeToken(true);
        confirmLogoutEvent.call();
    }

    public void clickCancel() {
        cancelLogoutEvent.call();
    }

    public void clickChangePw() {
        changePwEvent.call();
    }

    public void clickMyProfile() {
        myProfileEvent.call();
    }

    public void clickMyAd() {
        myAdEvent.call();
    }

    public void setLogoutEvent() {
        logoutEvent.call();
    }
}
