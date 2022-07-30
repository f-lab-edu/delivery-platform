package org.flab.deliveryplatform.member.interfaces.web;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformResponse;
import org.flab.deliveryplatform.member.application.port.SignUpMemberUseCase;
import org.flab.deliveryplatform.member.application.port.dto.SignUpMemberCommand;
import org.flab.deliveryplatform.member.application.port.dto.SignUpMemberResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@MemberRestController
@RequiredArgsConstructor
public class SignUpMemberController {

    private final SignUpMemberUseCase signUpMemberUseCase;

    @PostMapping("/signUp")
    @ResponseStatus(code = HttpStatus.OK)
    public DeliveryPlatformResponse<SignUpMemberResult> signUp(@RequestBody SignUpMemberCommand signUpMemberCommand) {
        return DeliveryPlatformResponse.ok(signUpMemberUseCase.signUp(signUpMemberCommand));
    }
}
