package com.add.ad.presentation.mapper;

import com.add.ad.domain.base.Mapper;
import com.add.ad.domain.entity.Auth;
import com.add.ad.presentation.entity.AuthModel;

public class AuthModelMapper implements Mapper<AuthModel, Auth> {
    @Override
    public Auth mapFrom(AuthModel from) {
        return new Auth(from.getUserEmail(), from.getUserPassword());
    }
}
