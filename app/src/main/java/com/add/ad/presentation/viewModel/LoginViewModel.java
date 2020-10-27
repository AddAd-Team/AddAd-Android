package com.add.ad.presentation.viewModel;

import android.util.Log;

import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

import com.add.ad.data.local.SharedPref;
import com.add.ad.data.repository.AuthRepository;
import com.add.ad.entity.Auth;
import com.add.ad.entity.Token;
import com.add.ad.presentation.base.BaseViewModel;
import com.add.ad.presentation.base.SingleLiveEvent;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class LoginViewModel extends BaseViewModel {
    CompositeDisposable compositeDisposable;
    AuthRepository authRepository;
    SharedPref sharedPref;

    public MutableLiveData<String> userId = new MutableLiveData<>();
    public MutableLiveData<String> userPassword = new MutableLiveData<>();

    public SingleLiveEvent<Void> startMain = new SingleLiveEvent<>();
    public SingleLiveEvent<String> idErrorEvent = new SingleLiveEvent<>();
    public SingleLiveEvent<String> pwErrorEvent = new SingleLiveEvent<>();

    @ViewModelInject
    public LoginViewModel(AuthRepository authRepository, CompositeDisposable compositeDisposable, SharedPref sharedPref, @Assisted SavedStateHandle savedStateHandle) {
        this.authRepository = authRepository;
        this.compositeDisposable = compositeDisposable;
        this.sharedPref = sharedPref;
    }

    public void login() {
        Auth auth = new Auth(userId.getValue(), userPassword.getValue());

        Log.d("pw",userPassword.getValue());
        compositeDisposable.add(
                authRepository.signIn(auth)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(it -> hi(it), t -> repoFailure(t)));

    }


    private void hi(Response<Token> data) {
        if(data.code()/2 == 100 ){
            sharedPref.saveToken(data.body() != null ? data.body().getAccessToken() : null,true);
            startMain.call();
            createToastEvent.setValue("로그인 성공");
        }else{
            idErrorEvent.setValue("아이디가 일치하지 않습니다.");
            pwErrorEvent.setValue("비밀번호가 일치하지 않습니다.");
        }
    }

    private void repoFailure(Throwable t) {
        createToastEvent.setValue(t.getMessage());
    }
}
