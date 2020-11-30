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

    public MutableLiveData<Boolean> myAdResult = new MutableLiveData<>(true);
    public MutableLiveData<Boolean> userInfo = new MutableLiveData<>(true);
    public MutableLiveData<Integer> adPosition = new MutableLiveData<>();
    public MutableLiveData<ArrayList<ResponseMyAdInfo>> creatorAdList = new MutableLiveData<>();
    public MutableLiveData<ArrayList<ResponseMyAdInfo>> advertiserAdList = new MutableLiveData<>();

    public SingleLiveEvent<Void> stopProgressEvent = new SingleLiveEvent<>();
    public SingleLiveEvent<Void> clickSelectEvent = new SingleLiveEvent<>();
    public SingleLiveEvent<Void> creatorAdEvent = new SingleLiveEvent<>();
    public SingleLiveEvent<Void> advertiserAdEvent = new SingleLiveEvent<>();
    public SingleLiveEvent<Void> clickApplyListEvent = new SingleLiveEvent<>();
    public SingleLiveEvent<Void> clickDeleteEvent = new SingleLiveEvent<>();

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
                        stopProgressEvent.call();
                        if (getUserInfo()) {
                            creatorAdList.setValue(it.body());
                            creatorAdEvent.call();
                        } else {
                            advertiserAdList.setValue(it.body());
                            advertiserAdEvent.call();
                        }
                    }
                }, it -> {
                    stopProgressEvent.call();
                    Log.e("error", it.getMessage());
                }));
    }

    private Boolean getUserInfo() {
        if (sharedPref.getInfo().equals("creator")) {
            return true;
        } else if (sharedPref.getInfo().equals("advertiser")) {
            return false;
        }
        return false;
    }

    public void clickBack() {
        backEvent.call();
    }

    public void clickApplyList() {
        clickApplyListEvent.call();
    }

    public void clickDelete() {
        createProgressEvent.call();

        compositeDisposable.add(myPageRepository.deletePost(adPosition.getValue())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(it -> {
                    if (it.code() == 200) {
                        dismissProgressEvent.call();
                        clickDeleteEvent.call();
                        createSnackEvent.setValue("글 삭제 성공");
                    }
                }, it -> {
                    createToastEvent.setValue(it.getMessage());
                }));
    }
}
