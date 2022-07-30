package org.flab.deliveryplatform.owner.interfaces.web;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.auth.AuthorizationData;
import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformResponse;
import org.flab.deliveryplatform.owner.application.port.LoginOwnerUseCase;
import org.flab.deliveryplatform.owner.application.port.dto.LoginOwnerCommand;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@OwnerRestController
@RequiredArgsConstructor
public class LoginOwnerController {

    private final LoginOwnerUseCase loginOwnerUseCase;

    @PostMapping("/login")
    @ResponseStatus(code = HttpStatus.OK)
    public DeliveryPlatformResponse<AuthorizationData> login(
        @RequestBody LoginOwnerCommand loginOwnerCommand) {
        return DeliveryPlatformResponse.ok(loginOwnerUseCase.login(loginOwnerCommand));
    }
}
