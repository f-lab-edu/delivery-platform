package org.flab.deliveryplatform.common.auth;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthorizationData {

    private String accessToken;

    private Long userId;

    private LocalDateTime issueDate;
}
