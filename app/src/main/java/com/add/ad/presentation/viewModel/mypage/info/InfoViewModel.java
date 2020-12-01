package com.add.ad.presentation.viewModel.mypage.info;

import androidx.hilt.lifecycle.ViewModelInject;

import com.add.ad.presentation.base.BaseViewModel;

public class InfoViewModel extends BaseViewModel {

    @ViewModelInject
    public InfoViewModel() { }

    public void clickBack() {
        backEvent.call();
    }
}
