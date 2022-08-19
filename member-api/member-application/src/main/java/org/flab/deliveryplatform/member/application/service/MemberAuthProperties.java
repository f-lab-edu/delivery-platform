package org.flab.deliveryplatform.member.application.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConstructorBinding
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "auth")
public class MemberAuthProperties {

    private final Token token;

    @Getter
    @RequiredArgsConstructor
    public static class Token {

        private final Long accessTokenExpiredTimeMillis;
    }
}
