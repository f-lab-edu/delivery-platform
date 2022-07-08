package org.flab.deliveryplatform.member.domain.authorization;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Authorization {

    private AuthorizationId authorizationId;

    private Long memberId;

    @Builder
    private Authorization(AuthorizationId authorizationId, Long memberId) {
        this.authorizationId = authorizationId;
        this.memberId = memberId;
    }
}
