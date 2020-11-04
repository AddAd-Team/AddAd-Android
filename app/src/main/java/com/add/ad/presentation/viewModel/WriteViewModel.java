package com.add.ad.presentation.viewModel;

import android.util.Log;

import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

import com.add.ad.data.repository.WriteRepository;
import com.add.ad.entity.Post;
import com.add.ad.presentation.base.BaseViewModel;
import com.add.ad.presentation.base.SingleLiveEvent;
import com.add.ad.presentation.util.FileUtil;

import java.util.Objects;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class WriteViewModel extends BaseViewModel {
    CompositeDisposable compositeDisposable;
    WriteRepository writeRepository;

    @ViewModelInject
    public WriteViewModel(WriteRepository writeRepository, CompositeDisposable compositeDisposable, @Assisted SavedStateHandle savedStateHandle) {
        this.writeRepository = writeRepository;
        this.compositeDisposable = compositeDisposable;
    }

    public MutableLiveData<String> adTitle = new MutableLiveData<>();
    public MutableLiveData<String> adTag = new MutableLiveData<>();
    public MutableLiveData<String> adContent = new MutableLiveData<>();
    public MutableLiveData<String> adPrice = new MutableLiveData<>("0");
    public MutableLiveData<String> postEndYear = new MutableLiveData<>("2020");
    public MutableLiveData<String> postEndMonth = new MutableLiveData<>("11");
    public MutableLiveData<String> postEndDay = new MutableLiveData<>("06");
    public MutableLiveData<String> adEndYear = new MutableLiveData<>("2020");
    public MutableLiveData<String> adEndMonth = new MutableLiveData<>("11");
    public MutableLiveData<String> adEndDay = new MutableLiveData<>("06");
    public MutableLiveData<String> adImageUri = new MutableLiveData<>();

    public SingleLiveEvent<Void> clickComplete = new SingleLiveEvent<>();
    public SingleLiveEvent<Void> clickNextEvent = new SingleLiveEvent<>();
    public SingleLiveEvent<Void> selectImageEvent = new SingleLiveEvent<>();

    public void clickNext() {
        clickNextEvent.call();
    }

    public void selectImage() {
        selectImageEvent.call();
    }

    public void selectComplete() {
        String postEndDate = postEndYear.getValue() + postEndMonth.getValue() + postEndDay.getValue();
        String adEndDate = adEndYear.getValue() + adEndMonth.getValue() + adEndDay.getValue();

        MultipartBody.Part file = FileUtil.createMultiPart(adTitle.getValue());
        RequestBody requestAdTitle = RequestBody.create(MediaType.parse("multipart/form-data"), Objects.requireNonNull(adTitle.getValue()));
        RequestBody requestAdTag = RequestBody.create(MediaType.parse("multipart/form-data"), Objects.requireNonNull(adTag.getValue()));
        RequestBody requestAdContent = RequestBody.create(MediaType.parse("multipart/form-data"), Objects.requireNonNull(adContent.getValue()));
        RequestBody requestAdPrice = RequestBody.create(MediaType.parse("multipart/form-data"), Objects.requireNonNull(adPrice.getValue()));
        RequestBody requestAdPostEndDate = RequestBody.create(MediaType.parse("multipart/form-data"), postEndDate);
        RequestBody requestAdEndDate = RequestBody.create(MediaType.parse("multipart/form-data"), adEndDate);

        compositeDisposable.add(writeRepository.postWrite(file, requestAdTitle, requestAdTag, requestAdContent, requestAdPrice, requestAdPostEndDate, requestAdEndDate)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(it -> {

                }));

    }
}
