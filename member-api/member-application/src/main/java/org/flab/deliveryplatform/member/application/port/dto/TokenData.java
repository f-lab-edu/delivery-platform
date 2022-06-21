package org.flab.deliveryplatform.member.application.port.dto;

import lombok.Getter;

@Getter
public class TokenData {

    private String accessToken;

    public TokenData(String accessToken) {
        this.accessToken = accessToken;
    }
}
