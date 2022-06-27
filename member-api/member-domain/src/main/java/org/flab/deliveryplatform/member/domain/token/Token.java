package org.flab.deliveryplatform.member.domain.token;

import lombok.Getter;

@Getter
public class Token {

    private TokenKey tokenKey;
    private TokenValue tokenValue;

    public Token(TokenKey tokenKey, TokenValue tokenValue) {
        this.tokenKey = tokenKey;
        this.tokenValue = tokenValue;
    }
}
