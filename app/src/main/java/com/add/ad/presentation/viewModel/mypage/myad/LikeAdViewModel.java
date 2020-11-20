package com.add.ad.presentation.viewModel.mypage.myad;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;

import com.add.ad.data.repository.mypage.MyPageRepository;
import com.add.ad.entity.response.ResponseMyAdInfo;
import com.add.ad.presentation.base.BaseViewModel;
import com.add.ad.presentation.base.SingleLiveEvent;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class LikeAdViewModel extends BaseViewModel {
    CompositeDisposable compositeDisposable;
    MyPageRepository myPageRepository;

    public MutableLiveData<ArrayList<ResponseMyAdInfo>> likeAdList = new MutableLiveData<>();

    public SingleLiveEvent<Void> likeAdEvent = new SingleLiveEvent<>();

    @ViewModelInject
    public LikeAdViewModel(CompositeDisposable compositeDisposable, MyPageRepository myPageRepository) {
        this.compositeDisposable = compositeDisposable;
        this.myPageRepository = myPageRepository;
    }

    public void getLikeAdList() {
        compositeDisposable.add(myPageRepository.getLikeAdList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(it -> {
                    if (it.code() == 200) {
                        likeAdList.setValue(it.body());
                        likeAdEvent.call();
                    }
                }, it -> {
                    createToastEvent.setValue("알 수 없는 오류가 발생했습니다.");
                }));
    }

    public void clickBack() {
        backEvent.call();
    }
}
