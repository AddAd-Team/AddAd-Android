package com.add.ad.presentation.di.modules.main.write;

import com.add.ad.data.api.Api;
import com.add.ad.data.repository.WriteRepository;
import com.add.ad.data.repository.WriteRepositoryImpl;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class WriteModule {

    @Provides
    public WriteRepository provideWriteRepo(Api api) {
        return new WriteRepositoryImpl(api);
    }
}
