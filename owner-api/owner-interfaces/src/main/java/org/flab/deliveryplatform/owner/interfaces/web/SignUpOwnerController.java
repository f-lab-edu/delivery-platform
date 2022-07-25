package org.flab.deliveryplatform.owner.interfaces.web;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformResponse;
import org.flab.deliveryplatform.owner.application.port.SignUpOwnerUseCase;
import org.flab.deliveryplatform.owner.application.port.dto.SignUpOwnerCommand;
import org.flab.deliveryplatform.owner.application.port.dto.SignUpOwnerResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@OwnerRestController
@RequiredArgsConstructor
public class SignUpOwnerController {

    private final SignUpOwnerUseCase signUpOwnerUseCase;

    @PostMapping
    public ResponseEntity<DeliveryPlatformResponse<SignUpOwnerResult>> signUp(
        @RequestBody SignUpOwnerCommand signUpOwnerCommand) {
        return ResponseEntity.ok(DeliveryPlatformResponse.ok(
            signUpOwnerUseCase.signUp(signUpOwnerCommand)
        ));
    }
}
