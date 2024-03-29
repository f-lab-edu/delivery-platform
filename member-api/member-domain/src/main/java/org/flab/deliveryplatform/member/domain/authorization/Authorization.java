package org.flab.deliveryplatform.member.domain.authorization;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import org.flab.deliveryplatform.common.auth.TokenType;

@Getter
public class Authorization {

    private String accessToken;

    private TokenType tokenType;

    private Long memberId;

    private LocalDateTime issueDate;

    private long accessTokenExpiredTimeSecs;

    @Builder
    public Authorization(String accessToken, TokenType tokenType, Long memberId,
        LocalDateTime issueDate, long accessTokenExpiredTimeSecs) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
        this.memberId = memberId;
        this.issueDate = issueDate;
        this.accessTokenExpiredTimeSecs = accessTokenExpiredTimeSecs;
    }

}
