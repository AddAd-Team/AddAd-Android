package com.add.ad.presentation.viewModel.feed;

import android.util.Log;

import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

import com.add.ad.data.repository.feed.FeedRepository;
import com.add.ad.entity.response.ResponseFeedInfo;
import com.add.ad.presentation.base.BaseViewModel;
import com.add.ad.presentation.base.SingleLiveEvent;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class FeedViewModel extends BaseViewModel {
    FeedRepository feedRepository;
    CompositeDisposable compositeDisposable;

    public MutableLiveData<ArrayList<ResponseFeedInfo>> feedList = new MutableLiveData<>();
    public SingleLiveEvent<Void> feedListEvent = new SingleLiveEvent<>();

    @ViewModelInject
    public FeedViewModel(FeedRepository feedRepository, CompositeDisposable compositeDisposable, @Assisted SavedStateHandle savedStateHandle) {
        this.feedRepository = feedRepository;
        this.compositeDisposable = compositeDisposable;
    }

    public void getFeed() {
        Log.d("getFeed", String.valueOf(feedList));
        compositeDisposable.add(feedRepository.getFeed()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(it -> {
                    Log.d("itcode", String.valueOf(it.code()));
                    if(it.code() == 200){
                        if (it.body() != null) {
                            feedList.setValue(new ArrayList<>(it.body()));
                            feedListEvent.call();
                        }
                        Log.d("feedList", String.valueOf(feedList.getValue().size()));
                    }
                }, it -> {
                    Log.e("errorFeed",it.getMessage());
                }));
    }
}