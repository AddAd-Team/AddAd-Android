package com.add.ad.presentation.viewModel.mypage.profile;

import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

import com.add.ad.data.repository.mypage.MyPageRepository;
import com.add.ad.entity.response.ResponseUserInfo;
import com.add.ad.presentation.base.BaseViewModel;
import com.add.ad.presentation.base.SingleLiveEvent;
import com.add.ad.presentation.util.FileUtil;

import java.util.Objects;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ProfileViewModel extends BaseViewModel {
    MyPageRepository myPageRepository;
    CompositeDisposable compositeDisposable;

    public MutableLiveData<Boolean> isEdit = new MutableLiveData<>(false);

    public MutableLiveData<ResponseUserInfo> userProfile = new MutableLiveData<>();
    public MutableLiveData<String> profileImageUri = new MutableLiveData<>(" ");
    public MutableLiveData<String> profileNameEt = new MutableLiveData<>();
    public MutableLiveData<String> profileTagEt = new MutableLiveData<>();
    public MutableLiveData<String> profileDescriptionEt = new MutableLiveData<>();

    public SingleLiveEvent<Void> profileEditEvent = new SingleLiveEvent<>();
    public SingleLiveEvent<Void> profileEditCompleteEvent = new SingleLiveEvent<>();
    public SingleLiveEvent<Void> profileImageEditEvent = new SingleLiveEvent<>();

    @ViewModelInject
    public ProfileViewModel(MyPageRepository myPageRepository, CompositeDisposable compositeDisposable, @Assisted SavedStateHandle savedStateHandle) {
        this.myPageRepository = myPageRepository;
        this.compositeDisposable = compositeDisposable;
    }

    public void getMyProfile() {
        compositeDisposable.add(myPageRepository.getUserProfile()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(it -> {
                    if (it.code() == 200) {
                        userProfile.setValue(it.body());
                    }
                }, it -> createToastEvent.setValue("알 수 없는 오류가 발생하였습니다.")));
    }

    public void clickEdit() {
        if (!isEdit.getValue()) {
            profileEditEvent.call();
            isEdit.setValue(true);
        } else {
            editProfile();
        }
    }

    public void  editProfile() {
        RequestBody requestProfileName = RequestBody.create(MediaType.parse("multipart/form-data"), Objects.requireNonNull(profileNameEt.getValue()));
        RequestBody requestProfileTag = RequestBody.create(MediaType.parse("multipart/form-data"), Objects.requireNonNull(profileTagEt.getValue()));
        RequestBody requestProfileDescription = RequestBody.create(MediaType.parse("multipart/form-data"), Objects.requireNonNull(profileDescriptionEt.getValue()));

        MultipartBody.Part file = FileUtil.createMultiPart(profileImageUri.getValue());

        compositeDisposable.add(myPageRepository.editProfile(file, requestProfileName, requestProfileDescription, requestProfileTag)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(it -> {
                    if (it.code() == 200) {
                        profileEditCompleteEvent.call();
                        createToastEvent.setValue("변경 성공");
                        isEdit.setValue(false);
                    }
                }, it -> createToastEvent.setValue("알 수 없는 오류가 발생하였습니다.")));
    }

    public void clickImageEdit() {
        profileImageEditEvent.call();
    }
}
