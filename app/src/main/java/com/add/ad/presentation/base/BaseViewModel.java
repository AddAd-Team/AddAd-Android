package com.add.ad.presentation.base;

import androidx.lifecycle.ViewModel;

public abstract class BaseViewModel extends ViewModel {
    SingleLiveEvent<String> createToastEvent = new SingleLiveEvent<>();
}
