package org.flab.deliveryplatform.member.interfaces.web;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformResponse;
import org.flab.deliveryplatform.member.application.port.LoginMemberUseCase;
import org.flab.deliveryplatform.member.application.port.dto.AuthorizationData;
import org.flab.deliveryplatform.member.application.port.dto.LoginMemberCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@MemberRestController
public class LoginMemberController {

    private final LoginMemberUseCase loginMemberUseCase;

    @PostMapping("/login")
    public ResponseEntity<DeliveryPlatformResponse<AuthorizationData>> loginMember(
        @RequestBody LoginMemberCommand command) {
        AuthorizationData tokenData = loginMemberUseCase.login(command);
        return ResponseEntity.ok(DeliveryPlatformResponse.ok(tokenData));
    }
}
