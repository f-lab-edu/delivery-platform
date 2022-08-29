package org.flab.deliveryplatform.owner.domain.auth;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import org.flab.deliveryplatform.common.auth.TokenType;

@Getter
public class Authorization {

    private String accessToken;

    private TokenType tokenType;

    private Long ownerId;

    private LocalDateTime issueDate;

    private long accessTokenExpiredTimeMillis;

    @Builder
    public Authorization(String accessToken, TokenType tokenType, Long ownerId,
        LocalDateTime issueDate, long accessTokenExpiredTimeMillis) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.ownerId = ownerId;
        this.issueDate = issueDate;
        this.accessTokenExpiredTimeMillis = accessTokenExpiredTimeMillis;
    }
}
