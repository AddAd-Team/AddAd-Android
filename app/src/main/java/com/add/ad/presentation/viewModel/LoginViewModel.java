package com.add.ad.presentation.viewModel;

import androidx.lifecycle.MutableLiveData;

import com.add.ad.domain.usecase.LoginUseCase;
import com.add.ad.presentation.base.BaseViewModel;
import com.add.ad.presentation.entity.AuthModel;
import com.add.ad.presentation.entity.TokenModel;
import com.add.ad.presentation.mapper.AuthModelMapper;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableSingleObserver;
import retrofit2.Response;

public class LoginViewModel extends BaseViewModel {
    LoginUseCase loginUseCase;
    AuthModelMapper authModelMapper;
    public MutableLiveData<String> userId = new MutableLiveData<>();
    public MutableLiveData<String> userPassword = new MutableLiveData<>();
    public MutableLiveData<AuthModel> user = new MutableLiveData<>();

    public LoginViewModel(LoginUseCase loginUseCase, AuthModelMapper authModelMapper) {
        this.loginUseCase = loginUseCase;
        this.authModelMapper = authModelMapper;
    }

    public void login(){
        AuthModel auth = new AuthModel(userId.getValue(), userPassword.getValue());
        loginUseCase.execute(authModelMapper.mapFrom(auth), new DisposableSingleObserver<Response<TokenModel>>() {
            @Override
            public void onSuccess(@NonNull Response<TokenModel> data) {
                switch (data.code()){
                    case 200:{

                    }
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
    }
}
