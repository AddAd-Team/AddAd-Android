package com.add.ad.presentation.mapper;

import com.add.ad.domain.base.Mapper;
import com.add.ad.domain.entity.Token;
import com.add.ad.presentation.entity.TokenModel;

public class TokenModelMapper implements Mapper<Token, TokenModel> {
    @Override
    public TokenModel mapFrom(Token from) {
        return new TokenModel(from.getAccessToken(), from.getRefreshToken());
    }
}
