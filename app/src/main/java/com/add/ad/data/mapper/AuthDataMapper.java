package com.add.ad.data.mapper;

import com.add.ad.data.entity.AuthData;
import com.add.ad.domain.base.Mapper;
import com.add.ad.domain.entity.Auth;

public class AuthDataMapper implements Mapper<Auth, AuthData> {
    @Override
    public AuthData mapFrom(Auth from) {
        return new AuthData(from.getUserEmail(),from.getUserPassword());
    }
}
