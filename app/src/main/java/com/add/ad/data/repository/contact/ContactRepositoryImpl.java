package com.add.ad.data.repository.contact;

import com.add.ad.data.api.Api;
import com.add.ad.entity.response.ResponseContactInfo;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Response;

public class ContactRepositoryImpl implements ContactRepository {
    Api api;

    @Inject
    public ContactRepositoryImpl(Api api) {
        this.api = api;
    }

    @Override
    public Single<Response<ArrayList<ResponseContactInfo>>> getContactList() {
        return api.getContactList();
    }
}
