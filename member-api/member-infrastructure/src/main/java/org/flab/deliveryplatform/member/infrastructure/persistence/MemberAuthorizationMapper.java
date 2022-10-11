package org.flab.deliveryplatform.member.infrastructure.persistence;

import org.flab.deliveryplatform.member.domain.authorization.Authorization;

public interface MemberAuthorizationMapper {

    static Authorization from(RedisAuthorization redisAuthorization) {
        return Authorization.builder()
            .accessToken(redisAuthorization.getAccessToken())
            .tokenType(redisAuthorization.getTokenType())
            .memberId(redisAuthorization.getMemberId())
            .issueDate(redisAuthorization.getIssueDate())
            .accessTokenExpiredTimeSecs(redisAuthorization.getAccessTokenExpiredTimeSecs())
            .build();
    }

    static RedisAuthorization from(Authorization authorization) {
        return RedisAuthorization.builder()
            .accessToken(authorization.getAccessToken())
            .tokenType(authorization.getTokenType())
            .memberId(authorization.getMemberId())
            .issueDate(authorization.getIssueDate())
            .accessTokenExpiredTimeSecs(authorization.getAccessTokenExpiredTimeSecs())
            .build();
    }
}
