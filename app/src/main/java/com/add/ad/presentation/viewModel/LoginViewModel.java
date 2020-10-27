package com.add.ad.presentation.viewModel;

import android.util.Log;

import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

import com.add.ad.domain.entity.Token;
import com.add.ad.domain.usecase.LoginUseCase;
import com.add.ad.presentation.base.BaseViewModel;
import com.add.ad.presentation.base.SingleLiveEvent;
import com.add.ad.presentation.entity.AuthModel;
import com.add.ad.presentation.mapper.AuthModelMapper;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import retrofit2.Response;

public class LoginViewModel extends BaseViewModel {
    LoginUseCase loginUseCase;
    AuthModelMapper authModelMapper;

    public MutableLiveData<String> userId = new MutableLiveData<>();
    public MutableLiveData<String> userPassword = new MutableLiveData<>();

    public SingleLiveEvent<Void> startMain = new SingleLiveEvent<>();
    public SingleLiveEvent<String> idErrorEvent = new SingleLiveEvent<>();
    public SingleLiveEvent<String> pwErrorEvent = new SingleLiveEvent<>();

    @ViewModelInject
    public LoginViewModel(LoginUseCase loginUseCase, AuthModelMapper authModelMapper, @Assisted SavedStateHandle savedStateHandle ) {
        this.loginUseCase = loginUseCase;
        this.authModelMapper = authModelMapper;
        Log.d("dfd","dfs");
    }

    public void login(){
        Log.d("sss",":aa");
        AuthModel auth = new AuthModel(userId.getValue(), userPassword.getValue());
        loginUseCase.execute(authModelMapper.mapFrom(auth), new DisposableSingleObserver<Response<Token>>() {
            @Override
            public void onSuccess(@NonNull Response<Token> data) {
                Log.d("daata", String.valueOf(data.code()));
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("fail",e.getMessage());
            }
        });
    }
}
