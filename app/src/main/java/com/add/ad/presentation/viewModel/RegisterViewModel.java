package com.add.ad.presentation.viewModel;

import android.util.Log;

import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

import com.add.ad.data.repository.AuthRepository;
import com.add.ad.presentation.base.BaseViewModel;
import com.add.ad.presentation.base.SingleLiveEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class RegisterViewModel extends BaseViewModel {
    CompositeDisposable compositeDisposable;
    AuthRepository authRepository;

    public MutableLiveData<String> userEmail = new MutableLiveData<>();
    public MutableLiveData<String> userPassword = new MutableLiveData<>();
    public MutableLiveData<String> userPasswordCheck = new MutableLiveData<>();
    public MutableLiveData<String> emailVerifyCode = new MutableLiveData<>();
    public MutableLiveData<String> userNickname = new MutableLiveData<>();
    public MutableLiveData<String> userTag = new MutableLiveData<>();

    public SingleLiveEvent<Void> viewVerifyCode = new SingleLiveEvent<>();
    public SingleLiveEvent<Void> confirmVerifyCode = new SingleLiveEvent<>();
    public SingleLiveEvent<Void> clearErrorEvent = new SingleLiveEvent<>();
    public SingleLiveEvent<Void> startLogin = new SingleLiveEvent<>();
    public SingleLiveEvent<Void> startNextRegister = new SingleLiveEvent<>();
    public SingleLiveEvent<String> emailErrorEvent = new SingleLiveEvent<>();
    public SingleLiveEvent<String> pwErrorEvent = new SingleLiveEvent<>();
    public SingleLiveEvent<String> verifyErrorEvent = new SingleLiveEvent<>();

    @ViewModelInject
    public RegisterViewModel(AuthRepository authRepository, CompositeDisposable compositeDisposable, @Assisted SavedStateHandle savedStateHandle) {
        this.authRepository = authRepository;
        this.compositeDisposable = compositeDisposable;
    }

    public void clickSignUpNext() {
        clearErrorEvent.call();
        if (userEmail.getValue() != null && emailVerifyCode.getValue() != null && userPassword.getValue() != null && userPasswordCheck.getValue() != null) {
            if (userPassword.getValue().equals(userPasswordCheck.getValue())) {
                startNextRegister.call();
            } else pwErrorEvent.setValue("비밀번호가 일치하지 않습니다");
        } else createToastEvent.setValue("빈칸을 모두 채워주세요");
    }

    public void requestVerifyCode() {
        if (checkEmail(userEmail.getValue() + "")) {
            apiVerifyCode();
        } else emailErrorEvent.setValue("이메일 형식이 일치하지 않습니다");
    }

    private boolean checkEmail(String email) {
        String regex = "^[_a-zA-Z0-9-.]+@[.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public void confirmCode() {
        confirmVerifyCode.call();
    }

    private void apiVerifyCode() {
        compositeDisposable.add(
                authRepository.requestVerifyCode(userEmail.getValue())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(it -> {
                                    if (it.code() / 2 == 2) viewVerifyCode.call();
                                    else createToastEvent.setValue("알 수 없는 오류가 발생하였습니다.");
                                },
                                it -> createToastEvent.setValue("알 수 없는 오류가 발생하였습니다.")));
    }

}
