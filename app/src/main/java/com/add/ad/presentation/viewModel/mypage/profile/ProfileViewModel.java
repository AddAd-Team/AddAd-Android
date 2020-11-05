package com.add.ad.presentation.viewModel.mypage.profile;

import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

import com.add.ad.data.repository.mypage.MyPageRepository;
import com.add.ad.presentation.base.BaseViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ProfileViewModel extends BaseViewModel {
    MyPageRepository myPageRepository;
    CompositeDisposable compositeDisposable;

    public MutableLiveData<String> profileName = new MutableLiveData<>();
    public MutableLiveData<String> profileTag = new MutableLiveData<>();
    public MutableLiveData<String> profileImageUrl = new MutableLiveData<>();
    public MutableLiveData<String> profileDescription = new MutableLiveData<>();

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
                        profileImageUrl.setValue(it.body() != null ? it.body().getUserImage() : null);
                        profileName.setValue(it.body() != null ? it.body().getUserName() : null);
                        profileTag.setValue(it.body() != null ? it.body().getUserTag() : null);
                        profileDescription.setValue(it.body() != null ? it.body().getUserDescription() : null);
                    }
                }, it -> createToastEvent.setValue("알 수 없는 오류가 발생하였습니다.")));
    }
}
