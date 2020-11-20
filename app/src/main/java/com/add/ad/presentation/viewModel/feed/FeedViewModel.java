package com.add.ad.presentation.viewModel.feed;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;

import com.add.ad.data.local.SharedPref;
import com.add.ad.data.repository.feed.FeedRepository;
import com.add.ad.entity.response.ResponseFeedInfo;
import com.add.ad.presentation.base.BaseViewModel;
import com.add.ad.presentation.base.SingleLiveEvent;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class FeedViewModel extends BaseViewModel {
    FeedRepository feedRepository;
    CompositeDisposable compositeDisposable;
    SharedPref sharedPref;

    public MutableLiveData<ArrayList<ResponseFeedInfo>> feedList = new MutableLiveData<>();
    public MutableLiveData<ResponseFeedInfo> detailFeed = new MutableLiveData<>();
    public MutableLiveData<Boolean> likeClickable = new MutableLiveData<>(false);
    public MutableLiveData<Boolean> application = new MutableLiveData<>(false);

    public SingleLiveEvent<Void> feedDetailEvent = new SingleLiveEvent<>();
    public SingleLiveEvent<Void> feedListEvent = new SingleLiveEvent<>();

    @ViewModelInject
    public FeedViewModel(FeedRepository feedRepository, CompositeDisposable compositeDisposable, SharedPref sharedPref) {
        this.feedRepository = feedRepository;
        this.compositeDisposable = compositeDisposable;
        this.sharedPref = sharedPref;
    }

    public void getFeed() {
        compositeDisposable.add(feedRepository.getFeed()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(it -> {
                    if (it.code() == 200) {
                        if (it.body() != null) {
                            feedList.setValue(new ArrayList<>(it.body()));
                            feedListEvent.call();
                        }
                    }
                }, it -> createToastEvent.setValue("알 수 없는 오류가 발생하였습니다.")));
    }

    public void getDetailFeed(int position) {
        compositeDisposable.add(feedRepository.getDetailFeed(feedList.getValue().get(position).getFeedId())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(it -> {
                    if (it.code() == 200) {
                        detailFeed.setValue(it.body());
                        application.setValue(detailFeed.getValue().getApplied());
                    }
                }, it -> createToastEvent.setValue("알 수 없는 오류가 발생하였습니다.")));
    }

    public void clickBack() {
        backEvent.call();
    }

    public void postLikes(int position) {
        compositeDisposable.add(feedRepository.postLike(feedList.getValue().get(position).getFeedId())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(it -> {
                    Log.d("success Post", String.valueOf(it.code()));
                }, it -> {
                    createToastEvent.setValue(it.getMessage());
                }));
    }

    public void deleteLikes(int position) {
        compositeDisposable.add(feedRepository.deleteLike(feedList.getValue().get(position).getFeedId())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(it -> {
                    Log.d("success DELETE", String.valueOf(it.code()));
                }, it -> {
                    createToastEvent.setValue(it.getMessage());
                }));
    }

    public void clickApplyBtn() {
        if (sharedPref.getInfo(false).equals("creator")) {
            application.setValue(!application.getValue());

            if (application.getValue()) {
                compositeDisposable.add(feedRepository.postApply(detailFeed.getValue().getFeedId())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(it -> {
                            Log.d("success Apply", String.valueOf(it.code()));
                        }, it -> {
                            createToastEvent.setValue("알 수 없는 오류가 발생하였습니다.");
                            application.setValue(!application.getValue());
                        }));
            } else {
                compositeDisposable.add(feedRepository.deleteApply(detailFeed.getValue().getFeedId())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(it -> {
                            Log.d("delete Apply", String.valueOf(it.code()));
                        }, it -> {
                            createToastEvent.setValue("알 수 없는 오류가 발생하였습니다.");
                            application.setValue(!application.getValue());
                        }));
            }
        } else {
            createToastEvent.setValue("광고주는 신청이 불가능합니다.");
        }
    }
}