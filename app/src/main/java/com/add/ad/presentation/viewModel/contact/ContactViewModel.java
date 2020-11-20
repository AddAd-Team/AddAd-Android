package com.add.ad.presentation.viewModel.contact;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.MutableLiveData;

import com.add.ad.data.repository.contact.ContactRepository;
import com.add.ad.entity.response.ResponseContactInfo;
import com.add.ad.presentation.base.BaseViewModel;
import com.add.ad.presentation.base.SingleLiveEvent;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ContactViewModel extends BaseViewModel {
    ContactRepository contactRepository;
    CompositeDisposable compositeDisposable;

    public MutableLiveData<ArrayList<ResponseContactInfo>> contactList = new MutableLiveData<>();

    public SingleLiveEvent<Void> contactEvent = new SingleLiveEvent<>();

    @ViewModelInject
    public ContactViewModel(ContactRepository contactRepository, CompositeDisposable compositeDisposable) {
        this.contactRepository = contactRepository;
        this.compositeDisposable = compositeDisposable;
    }

    public void getContactList(){
        compositeDisposable.add(contactRepository.getContactList()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(it -> {
            if(it.code() == 200){
                contactList.setValue(it.body());
                contactEvent.call();
            }
        }, it -> {
            Log.e("error", "Errorororororo");
        }));
    }
}
