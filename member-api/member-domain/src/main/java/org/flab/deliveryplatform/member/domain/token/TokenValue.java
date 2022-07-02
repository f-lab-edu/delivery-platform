package org.flab.deliveryplatform.member.domain.token;

import lombok.Getter;

@Getter
public class TokenValue {

    private final Long id;

    public TokenValue(Long id) {
        this.id = id;
    }
}
