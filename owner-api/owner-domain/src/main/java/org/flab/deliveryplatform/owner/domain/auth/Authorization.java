package org.flab.deliveryplatform.owner.domain.auth;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Authorization {

    private String accessToken;

    private Long ownerId;

    private LocalDateTime issueDate;

    @Builder
    public Authorization(String accessToken, Long ownerId, LocalDateTime issueDate) {
        this.accessToken = accessToken;
        this.ownerId = ownerId;
        this.issueDate = issueDate;
    }
}
