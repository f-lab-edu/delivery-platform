package org.flab.deliveryplatform.member.interfaces.web;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformResponse;
import org.flab.deliveryplatform.member.application.port.SignUpMemberUseCase;
import org.flab.deliveryplatform.member.application.port.dto.SignUpMemberCommand;
import org.flab.deliveryplatform.member.application.port.dto.SignUpMemberResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@MemberRestController
@RequiredArgsConstructor
public class SignUpMemberController {

    private final SignUpMemberUseCase signUpMemberUseCase;

    @PostMapping
    public ResponseEntity<DeliveryPlatformResponse<SignUpMemberResult>> signUp(
        @RequestBody SignUpMemberCommand signUpMemberCommand) {
        return ResponseEntity.ok(DeliveryPlatformResponse.ok(
            signUpMemberUseCase.signUp(signUpMemberCommand)
        ));
    }
}
