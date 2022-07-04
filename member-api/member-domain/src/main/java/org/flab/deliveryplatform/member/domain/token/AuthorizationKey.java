package org.flab.deliveryplatform.member.domain.token;

import lombok.Getter;

@Getter
public class AuthorizationKey {

    private final String key;

    public AuthorizationKey(String key) {
        this.key = key;
    }
}
