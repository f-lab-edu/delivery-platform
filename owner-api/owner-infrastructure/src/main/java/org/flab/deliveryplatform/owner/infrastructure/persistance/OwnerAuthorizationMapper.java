package org.flab.deliveryplatform.owner.infrastructure.persistance;

import org.flab.deliveryplatform.owner.domain.auth.Authorization;

public interface OwnerAuthorizationMapper {

    static Authorization from(RedisAuthorization redisAuthorization) {
        return Authorization.builder()
            .accessToken(redisAuthorization.getAccessToken())
            .tokenType(redisAuthorization.getTokenType())
            .ownerId(redisAuthorization.getOwnerId())
            .issueDate(redisAuthorization.getIssueDate())
            .accessTokenExpiredTimeSecs(redisAuthorization.getAccessTokenExpiredTimeSecs())
            .build();
    }

    static RedisAuthorization from(Authorization authorization) {
        return RedisAuthorization.builder()
            .accessToken(authorization.getAccessToken())
            .tokenType(authorization.getTokenType())
            .ownerId(authorization.getOwnerId())
            .issueDate(authorization.getIssueDate())
            .accessTokenExpiredTimeSecs(authorization.getAccessTokenExpiredTimeSecs())
            .build();
    }
}
