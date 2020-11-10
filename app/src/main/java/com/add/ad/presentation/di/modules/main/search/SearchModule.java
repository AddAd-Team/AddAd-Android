package com.add.ad.presentation.di.modules.main.search;

import com.add.ad.data.api.Api;
import com.add.ad.data.repository.search.SearchRepository;
import com.add.ad.data.repository.search.SearchRepositoryImpl;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class SearchModule {

    @Provides
    public SearchRepository provideSearchRepository(
            Api api
    ) {
        return new SearchRepositoryImpl(api);
    }
}
