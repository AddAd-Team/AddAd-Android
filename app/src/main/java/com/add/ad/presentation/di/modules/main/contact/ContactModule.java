package com.add.ad.presentation.di.modules.main.contact;

import com.add.ad.data.api.Api;
import com.add.ad.data.repository.contact.ContactRepository;
import com.add.ad.data.repository.contact.ContactRepositoryImpl;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class ContactModule {

    @Provides
    public ContactRepository provideContactRepo(
            Api api
    ) {
        return new ContactRepositoryImpl(api);
    }

}
