package com.add.ad.presentation.viewModel.search;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;

import com.add.ad.data.repository.search.SearchRepository;
import com.add.ad.entity.response.ResponseSearchInfo;
import com.add.ad.presentation.base.BaseViewModel;
import com.add.ad.presentation.base.SingleLiveEvent;
import com.add.ad.presentation.util.TagList;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class SearchViewModel extends BaseViewModel {
    CompositeDisposable compositeDisposable;
    SearchRepository searchRepository;

    @ViewModelInject
    public SearchViewModel(CompositeDisposable compositeDisposable, SearchRepository searchRepository) {
        this.compositeDisposable = compositeDisposable;
        this.searchRepository = searchRepository;
    }

    public MutableLiveData<String> searchEt = new MutableLiveData<>(" ");
    public MutableLiveData<ArrayList<ResponseSearchInfo>> searchList = new MutableLiveData<>();
    public MutableLiveData<ResponseSearchInfo> detailSearch = new MutableLiveData<>();


    public SingleLiveEvent<Void> searchListEvent = new SingleLiveEvent<>();
    public SingleLiveEvent<Void> searchDetailEvent = new SingleLiveEvent<>();

    public void getCreatorList() {
        compositeDisposable.add(searchRepository.getCreators(0)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(it -> {
                    Log.d("code", String.valueOf(it.code()));
                    searchList.setValue(it.body());
                    searchListEvent.call();
                }, it -> {
                    Log.e("error", it.getMessage());
                }));
    }

    public void searchCreator() {
        compositeDisposable.add(searchRepository.searchCreator(0, searchEt.getValue())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(it -> {
                    if (it.code() == 200) {
                        searchList.setValue(it.body());
                        searchListEvent.call();
                    }
                }, it -> {
                    Log.e("error", it.getMessage());
                }));
    }

    public void searchTag(int position) {
        Log.d("position", TagList.getTagList().get(position));
        compositeDisposable.add(searchRepository.searchTag(0, TagList.getTagList().get(position))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(it -> {
                    switch (it.code()) {
                        case 200: {
                            searchList.setValue(it.body());
                            searchListEvent.call();
                        }
                    }
                }, it -> {
                    createToastEvent.setValue(it.getLocalizedMessage());
                }));
    }

    public void searchDetailCreator(int position) {
        compositeDisposable.add(searchRepository.getDetailCreator(searchList.getValue().get(position).getUserId())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(it -> {
                    Log.d("detailCode", String.valueOf(it.code()));
                    if(it.code() == 200){
                        detailSearch.setValue(it.body());
                        Log.d("detailCodeasdfasdfasdf", detailSearch.getValue().getUserEmail() + "dfadfa");
                    }
                }, it -> {
                    Log.e("detailError", it.getMessage());
                }));
    }
}
