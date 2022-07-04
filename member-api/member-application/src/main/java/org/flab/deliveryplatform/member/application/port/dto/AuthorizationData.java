package org.flab.deliveryplatform.member.application.port.dto;

import lombok.Getter;

@Getter
public class AuthorizationData {

    private String accessToken;

    public AuthorizationData(String accessToken) {
        this.accessToken = accessToken;
    }
}
