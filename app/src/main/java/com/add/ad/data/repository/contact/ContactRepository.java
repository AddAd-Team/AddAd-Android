package com.add.ad.data.repository.contact;

import com.add.ad.entity.response.ResponseContactInfo;

import java.util.ArrayList;

import io.reactivex.Single;
import retrofit2.Response;

public interface ContactRepository {
    Single<Response<ArrayList<ResponseContactInfo>>> getContactList();
}
