package org.flab.deliveryplatform.interfaces.member.web;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.web.DeliveryPlatformResponse;
import org.flab.deliveryplatform.member.application.port.SignUpMemberUseCase;
import org.flab.deliveryplatform.member.application.port.dto.SignUpMemberCommand;
import org.flab.deliveryplatform.member.application.port.dto.SignUpMemberResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class SignUpMemberController {

    private final SignUpMemberUseCase signUpMemberUseCase;

    @PostMapping("/signUp")
    public ResponseEntity<DeliveryPlatformResponse<SignUpMemberResult>> signUp(
        @RequestBody SignUpMemberCommand signUpMemberCommand) {
        return ResponseEntity.ok(DeliveryPlatformResponse.ok(
            signUpMemberUseCase.signUp(signUpMemberCommand)
        ));
    }
}
