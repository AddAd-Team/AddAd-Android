package com.add.ad.presentation.di.modules.main.mypage;

import com.add.ad.data.api.Api;
import com.add.ad.data.local.SharedPref;
import com.add.ad.data.repository.AuthRepository;
import com.add.ad.data.repository.AuthRepositoryImpl;
import com.add.ad.data.repository.mypage.MyPageRepository;
import com.add.ad.data.repository.mypage.MyPageRepositoryImpl;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class MyPageModule {

    @Provides
    public MyPageRepository provideMyPageRepo(
            Api api
    ) {
        return new MyPageRepositoryImpl(api);
    }

}
