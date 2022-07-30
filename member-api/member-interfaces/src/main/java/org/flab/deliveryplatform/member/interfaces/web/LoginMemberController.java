package org.flab.deliveryplatform.member.interfaces.web;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.auth.AuthorizationData;
import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformResponse;
import org.flab.deliveryplatform.member.application.port.LoginMemberUseCase;
import org.flab.deliveryplatform.member.application.port.dto.LoginMemberCommand;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequiredArgsConstructor
@MemberRestController
public class LoginMemberController {

    private final LoginMemberUseCase loginMemberUseCase;

    @PostMapping("/login")
    @ResponseStatus(code = HttpStatus.OK)
    public DeliveryPlatformResponse<AuthorizationData> loginMember(
        @RequestBody LoginMemberCommand command) {
        AuthorizationData authorizationData = loginMemberUseCase.login(command);
        return DeliveryPlatformResponse.ok(authorizationData);
    }
}
