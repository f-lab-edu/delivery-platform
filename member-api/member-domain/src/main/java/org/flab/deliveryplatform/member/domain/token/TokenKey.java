package org.flab.deliveryplatform.member.domain.token;

import lombok.Getter;

@Getter
public class TokenKey {

    private final String key;

    public TokenKey(String key) {
        this.key = key;
    }
}
