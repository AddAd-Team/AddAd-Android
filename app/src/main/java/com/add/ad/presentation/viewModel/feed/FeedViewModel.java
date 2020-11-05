package com.add.ad.presentation.viewModel.feed;

import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.SavedStateHandle;

import com.add.ad.data.repository.feed.FeedRepository;
import com.add.ad.presentation.base.BaseViewModel;

import io.reactivex.disposables.CompositeDisposable;

public class FeedViewModel extends BaseViewModel {
    FeedRepository feedRepository;
    CompositeDisposable compositeDisposable;

    @ViewModelInject
    public FeedViewModel(FeedRepository feedRepository, CompositeDisposable compositeDisposable, @Assisted SavedStateHandle savedStateHandle) {
        this.feedRepository = feedRepository;
        this.compositeDisposable = compositeDisposable;
    }

}
