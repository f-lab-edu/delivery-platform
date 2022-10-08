package org.flab.deliveryplatform.common.auth;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class AuthorizationData {

    private String accessToken;

    private TokenType tokenType;

    private Long userId;

    private int expiresIn;

    public AuthorizationData(String accessToken, TokenType tokenType, Long userId, LocalDateTime issueDate,
        long expiredTimeSecs) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.userId = userId;
        this.expiresIn = calculateExpiresIn(issueDate, expiredTimeSecs);
    }

    private int calculateExpiresIn(LocalDateTime issueDataTime, long expiredTimeSecs) {
        return (int) Math.max(0,
            expiredTimeSecs - (System.currentTimeMillis() - Timestamp.valueOf(issueDataTime).getTime()) / 1000);
    }
}
