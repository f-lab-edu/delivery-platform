package org.flab.deliveryplatform.member.domain.token;

import lombok.Getter;

@Getter
public class Authorization {

    private AuthorizationKey authorizationKey;
    private AuthorizationValue authorizationValue;

    public Authorization(AuthorizationKey tokenKey, AuthorizationValue authorizationValue) {
        this.authorizationKey = tokenKey;
        this.authorizationValue = authorizationValue;
    }
}
