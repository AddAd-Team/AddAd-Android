package com.add.ad.presentation.viewModel.mypage.change;

import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

import com.add.ad.data.repository.mypage.MyPageRepository;
import com.add.ad.presentation.base.BaseViewModel;
import com.add.ad.presentation.base.SingleLiveEvent;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ChangePasswordViewModel extends BaseViewModel {
    MyPageRepository myPageRepository;
    CompositeDisposable compositeDisposable;

    public MutableLiveData<String> newPassword = new MutableLiveData<>();
    public MutableLiveData<String> newPasswordCheck = new MutableLiveData<>();

    public SingleLiveEvent<Void> pwChangeEvent = new SingleLiveEvent<>();

    @ViewModelInject
    public ChangePasswordViewModel(MyPageRepository myPageRepository, CompositeDisposable compositeDisposable, @Assisted SavedStateHandle savedStateHandle) {
        this.myPageRepository = myPageRepository;
        this.compositeDisposable = compositeDisposable;
    }

    public void sendNewPassword() {
        if(newPassword.getValue() != null && newPasswordCheck.getValue() != null){
            if (newPassword.getValue().equals(newPasswordCheck.getValue())) {
                compositeDisposable.add(myPageRepository.changePassword(newPassword.getValue())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(it -> {
                            if (it.code() == 200) {
                                createToastEvent.setValue("비밀번호 변경 성공");
                                pwChangeEvent.call();
                            }else createToastEvent.setValue("알 수 없는 오류가 발생하였습니다.");
                        }, it -> createToastEvent.setValue(it.getMessage())));

            } else createToastEvent.setValue("비밀번호가 일치하지 않습니다.");
        } else createToastEvent.setValue("빈칸을 채워주세요.");
    }

    public void clickBack() {
        backEvent.call();
    }
}
