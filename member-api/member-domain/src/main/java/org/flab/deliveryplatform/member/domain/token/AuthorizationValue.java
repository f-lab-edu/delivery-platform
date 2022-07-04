package org.flab.deliveryplatform.member.domain.token;

import lombok.Getter;

@Getter
public class AuthorizationValue {

    private final Long id;

    public AuthorizationValue(Long id) {
        this.id = id;
    }
}
