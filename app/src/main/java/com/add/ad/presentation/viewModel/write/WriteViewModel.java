package com.add.ad.presentation.viewModel.write;

import android.util.Log;

import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

import com.add.ad.data.repository.write.WriteRepository;
import com.add.ad.presentation.base.BaseViewModel;
import com.add.ad.presentation.base.SingleLiveEvent;
import com.add.ad.presentation.util.FileUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

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

    Date currentTime = Calendar.getInstance().getTime();
    SimpleDateFormat dayFormat = new SimpleDateFormat("dd", Locale.getDefault());
    SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.getDefault());
    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.getDefault());

    public MutableLiveData<String> adTitle = new MutableLiveData<>();
    public MutableLiveData<String> adTag = new MutableLiveData<>();
    public MutableLiveData<String> adContent = new MutableLiveData<>();
    public MutableLiveData<String> adPrice = new MutableLiveData<>("0");
    public MutableLiveData<String> postEndYear = new MutableLiveData<>(yearFormat.format(currentTime));
    public MutableLiveData<String> postEndMonth = new MutableLiveData<>(monthFormat.format(currentTime));
    public MutableLiveData<String> postEndDay = new MutableLiveData<>(dayFormat.format(currentTime));
    public MutableLiveData<String> adEndYear = new MutableLiveData<>(yearFormat.format(currentTime));
    public MutableLiveData<String> adEndMonth = new MutableLiveData<>(monthFormat.format(currentTime));
    public MutableLiveData<String> adEndDay = new MutableLiveData<>(dayFormat.format(currentTime));
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

        MultipartBody.Part file = FileUtil.createMultiPart(adImageUri.getValue());
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
                    if(it.code() == 200){
                        clickComplete.call();
                        createToastEvent.setValue("글 올리기 성공");
                    }
                    Log.d("sasdfasdfa", String.valueOf(it.code()));
                }, it -> Log.e("sadfas", String.valueOf(it.getCause()))));

    }
}
