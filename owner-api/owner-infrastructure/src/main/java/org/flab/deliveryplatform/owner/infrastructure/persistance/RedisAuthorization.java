package org.flab.deliveryplatform.owner.infrastructure.persistance;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import org.flab.deliveryplatform.common.auth.TokenType;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@RedisHash("owner")
public class RedisAuthorization {

    @Id
    private String accessToken;

    private TokenType tokenType;

    private Long ownerId;

    private LocalDateTime issueDate;

    @TimeToLive
    private long accessTokenExpiredTimeSecs;

    @Builder
    public RedisAuthorization(String accessToken, TokenType tokenType, Long ownerId, LocalDateTime issueDate,
        long accessTokenExpiredTimeSecs) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.ownerId = ownerId;
        this.issueDate = issueDate;
        this.accessTokenExpiredTimeSecs = accessTokenExpiredTimeSecs;
    }
}
