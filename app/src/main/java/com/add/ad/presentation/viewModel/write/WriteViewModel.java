package com.add.ad.presentation.viewModel.write;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;

import com.add.ad.data.local.SharedPref;
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
    SharedPref sharedPref;

    @ViewModelInject
    public WriteViewModel(WriteRepository writeRepository, CompositeDisposable compositeDisposable, SharedPref sharedPref) {
        this.writeRepository = writeRepository;
        this.compositeDisposable = compositeDisposable;
        this.sharedPref = sharedPref;
    }

    public MutableLiveData<String> adTitle = new MutableLiveData<>();
    public MutableLiveData<String> adTag = new MutableLiveData<>();
    public MutableLiveData<String> adContent = new MutableLiveData<>();
    public MutableLiveData<String> adPrice = new MutableLiveData<>("0");
    public MutableLiveData<String> postEndYear = new MutableLiveData<>();
    public MutableLiveData<String> postEndMonth = new MutableLiveData<>();
    public MutableLiveData<String> postEndDay = new MutableLiveData<>();
    public MutableLiveData<String> adEndYear = new MutableLiveData<>();
    public MutableLiveData<String> adEndMonth = new MutableLiveData<>();
    public MutableLiveData<String> adEndDay = new MutableLiveData<>();
    public MutableLiveData<String> adImageUri = new MutableLiveData<>();

    public SingleLiveEvent<Void> clickComplete = new SingleLiveEvent<>();
    public SingleLiveEvent<Void> clickNextEvent = new SingleLiveEvent<>();
    public SingleLiveEvent<Void> selectImageEvent = new SingleLiveEvent<>();

    public void clickNext() {
        if (adTitle.getValue() != null && adTag.getValue() != null && adContent.getValue() != null) {
            clickNextEvent.call();
        } else createToastEvent.setValue("빈칸을 모두 채워주세요");
    }

    public void selectImage() {
        selectImageEvent.call();
    }

    public void selectComplete() {
        createProgressEvent.call();
        String postEndDate = postEndYear.getValue() + postEndMonth.getValue() + postEndDay.getValue();
        String adEndDate = adEndYear.getValue() + adEndMonth.getValue() + adEndDay.getValue();

        RequestBody requestAdTitle = RequestBody.create(MediaType.parse("multipart/form-data"), Objects.requireNonNull(adTitle.getValue()));
        RequestBody requestAdTag = RequestBody.create(MediaType.parse("multipart/form-data"), Objects.requireNonNull(adTag.getValue()));
        RequestBody requestAdContent = RequestBody.create(MediaType.parse("multipart/form-data"), Objects.requireNonNull(adContent.getValue()));
        RequestBody requestAdPrice = RequestBody.create(MediaType.parse("multipart/form-data"), Objects.requireNonNull(adPrice.getValue()));
        RequestBody requestAdPostEndDate = RequestBody.create(MediaType.parse("multipart/form-data"), postEndDate);
        RequestBody requestAdEndDate = RequestBody.create(MediaType.parse("multipart/form-data"), adEndDate);

        if (adImageUri.getValue() != null) {
            MultipartBody.Part file = FileUtil.createMultiPart(adImageUri.getValue());

            compositeDisposable.add(writeRepository.postWrite(file, requestAdTitle, requestAdTag, requestAdContent, requestAdPrice, requestAdPostEndDate, requestAdEndDate)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(it -> {
                        if (it.code() == 200) {
                            dismissProgressEvent.call();
                            clickComplete.call();
                            createSnackEvent.setValue("글 올리기 성공");
                        }
                    }, it -> {
                        dismissProgressEvent.call();
                        createToastEvent.setValue("알 수 없는 오류가 발생하였습니다.");
                    }));
        } else {
            createToastEvent.setValue("이미지를 선택해주세요.");
        }
    }

    public void clickBack() {
        backEvent.call();
    }
}
