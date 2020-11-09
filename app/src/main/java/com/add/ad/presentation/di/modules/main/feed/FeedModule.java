package com.add.ad.presentation.di.modules.main.feed;

import com.add.ad.data.api.Api;
import com.add.ad.data.repository.feed.FeedRepository;
import com.add.ad.data.repository.feed.FeedRepositoryImpl;
import com.add.ad.data.repository.mypage.MyPageRepository;
import com.add.ad.data.repository.mypage.MyPageRepositoryImpl;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class FeedModule {

    @Provides
    public FeedRepository provideFeedRepo(
            Api api
    ) {
        return new FeedRepositoryImpl(api);
    }

}
