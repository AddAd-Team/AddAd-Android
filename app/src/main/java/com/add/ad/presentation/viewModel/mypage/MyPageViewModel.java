package com.add.ad.presentation.viewModel.mypage;

import androidx.hilt.lifecycle.ViewModelInject;

import com.add.ad.data.local.SharedPref;
import com.add.ad.presentation.base.BaseViewModel;
import com.add.ad.presentation.base.SingleLiveEvent;

public class MyPageViewModel extends BaseViewModel {
    SharedPref sharedPref;

    @ViewModelInject
    public MyPageViewModel(SharedPref sharedPref){
        this.sharedPref = sharedPref;
    }

    public SingleLiveEvent<Void> logoutEvent = new SingleLiveEvent<>();
    public SingleLiveEvent<Void> myAdEvent = new SingleLiveEvent<>();
    public SingleLiveEvent<Void> myProfileEvent = new SingleLiveEvent<>();
    public SingleLiveEvent<Void> changePwEvent = new SingleLiveEvent<>();
    public SingleLiveEvent<Void> confirmLogoutEvent = new SingleLiveEvent<>();
    public SingleLiveEvent<Void> cancelLogoutEvent = new SingleLiveEvent<>();

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
