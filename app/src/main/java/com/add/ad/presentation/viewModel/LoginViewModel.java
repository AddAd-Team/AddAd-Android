package com.add.ad.presentation.viewModel;

import android.util.Log;

import androidx.hilt.Assisted;
import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

import com.add.ad.data.repository.AuthRepository;
import com.add.ad.entity.Auth;
import com.add.ad.entity.Token;
import com.add.ad.presentation.base.BaseViewModel;
import com.add.ad.presentation.base.SingleLiveEvent;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class LoginViewModel extends BaseViewModel {
    AuthRepository authRepository;

    public MutableLiveData<String> userId = new MutableLiveData<>();
    public MutableLiveData<String> userPassword = new MutableLiveData<>();

    public SingleLiveEvent<Void> startMain = new SingleLiveEvent<>();
    public SingleLiveEvent<String> idErrorEvent = new SingleLiveEvent<>();
    public SingleLiveEvent<String> pwErrorEvent = new SingleLiveEvent<>();

    @ViewModelInject
    public LoginViewModel( AuthRepository authRepository, @Assisted SavedStateHandle savedStateHandle ) {
        this.authRepository = authRepository;
        Log.d("dfd","dfs");
    }

    public void login(){
        Auth auth = new Auth(userId.getValue(), userPassword.getValue());

        authRepository.signIn(auth)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(it -> hi(it));
    }



    private void hi(Response<Token> data){
        Log.d("dff", String.valueOf(data.code()));
    }
}
