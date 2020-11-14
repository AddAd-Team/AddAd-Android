package com.add.ad.presentation.viewModel.feed;

import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

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

    public MutableLiveData<ArrayList<ResponseFeedInfo>> feedList = new MutableLiveData<>();
    public MutableLiveData<ResponseFeedInfo> detailFeed = new MutableLiveData<>();

    public SingleLiveEvent<Void> feedDetailEvent = new SingleLiveEvent<>();
    public SingleLiveEvent<Void> feedListEvent = new SingleLiveEvent<>();

    @ViewModelInject
    public FeedViewModel(FeedRepository feedRepository, CompositeDisposable compositeDisposable, @Assisted SavedStateHandle savedStateHandle) {
        this.feedRepository = feedRepository;
        this.compositeDisposable = compositeDisposable;
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
                    }
                }, it -> createToastEvent.setValue("알 수 없는 오류가 발생하였습니다.")));
    }

    public void clickBack(){
        backEvent.call();
    }
}