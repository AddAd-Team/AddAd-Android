package com.add.ad.data.mapper;

import com.add.ad.data.entity.TokenData;
import com.add.ad.domain.base.Mapper;
import com.add.ad.domain.entity.Token;

public class TokenDataMapper implements Mapper<TokenData, Token> {
    @Override
    public Token mapFrom(TokenData from) {
        return new Token(from.getAccessToken(), from.getRefreshToken());
    }
}
