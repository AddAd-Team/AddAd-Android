package com.add.ad.data.repository.mypage;

import io.reactivex.Single;
import retrofit2.Response;

public interface MyPageRepository {
    Single<Response<Void>> changePassword(String password);
}
