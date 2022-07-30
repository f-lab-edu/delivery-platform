package org.flab.deliveryplatform.member.domain.authorization;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Authorization {

    private String accessToken;

    private Long memberId;

    private LocalDateTime issueDate;

    @Builder
    private Authorization(String accessToken, Long memberId, LocalDateTime issueDate) {
        this.accessToken = accessToken;
        this.memberId = memberId;
        this.issueDate = issueDate;
    }
}
