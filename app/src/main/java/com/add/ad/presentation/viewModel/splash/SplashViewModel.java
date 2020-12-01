package com.add.ad.presentation.viewModel.splash;

import androidx.hilt.lifecycle.ViewModelInject;

import com.add.ad.data.local.SharedPref;
import com.add.ad.presentation.base.BaseViewModel;
import com.add.ad.presentation.base.SingleLiveEvent;

public class SplashViewModel extends BaseViewModel {
    SharedPref sharedPref;

    public SingleLiveEvent<Void> firstLandingEvent = new SingleLiveEvent<>();
    public SingleLiveEvent<Void> loginEvent = new SingleLiveEvent<>();

    @ViewModelInject
    public SplashViewModel(SharedPref sharedPref) {
        this.sharedPref = sharedPref;
    }

    public void startApp() {
        if (sharedPref.getDeviceToken().isEmpty()) {
            firstLandingEvent.call();
        } else loginEvent.call();

    }
}
