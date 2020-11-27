package com.add.ad.presentation.base;

import androidx.lifecycle.ViewModel;

public abstract class BaseViewModel extends ViewModel {
    public SingleLiveEvent<String> createSnackEvent = new SingleLiveEvent<>();
    public SingleLiveEvent<String> createToastEvent = new SingleLiveEvent<>();
    public SingleLiveEvent<Void> createProgressEvent = new SingleLiveEvent<>();
    public SingleLiveEvent<Void> dismissProgressEvent = new SingleLiveEvent<>();
    public SingleLiveEvent<Void> backEvent = new SingleLiveEvent<>();
}
