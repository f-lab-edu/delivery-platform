package org.flab.deliveryplatform.member.infrastructure.persistence;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import org.flab.deliveryplatform.common.auth.TokenType;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@RedisHash("member")
public class RedisAuthorization {

    @Id
    private String accessToken;

    private TokenType tokenType;

    private Long memberId;

    private LocalDateTime issueDate;

    @TimeToLive
    private long accessTokenExpiredTimeSecs;

    @Builder
    public RedisAuthorization(String accessToken, TokenType tokenType, Long memberId, LocalDateTime issueDate,
        long accessTokenExpiredTimeSecs) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.memberId = memberId;
        this.issueDate = issueDate;
        this.accessTokenExpiredTimeSecs = accessTokenExpiredTimeSecs;
    }
}
