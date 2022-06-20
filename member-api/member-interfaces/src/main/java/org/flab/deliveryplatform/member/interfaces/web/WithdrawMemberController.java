package org.flab.deliveryplatform.member.interfaces.web;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformResponse;
import org.flab.deliveryplatform.member.application.port.WithdrawMemberUseCase;
import org.flab.deliveryplatform.member.application.port.dto.WithdrawMemberCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

@MemberRestController
@RequiredArgsConstructor
public class WithdrawMemberController {

    private final WithdrawMemberUseCase withdrawMemberUseCase;

    @DeleteMapping
    public ResponseEntity<DeliveryPlatformResponse<Object>> withdraw(
        @RequestBody WithdrawMemberCommand withdrawMemberCommand) {
        withdrawMemberUseCase.withdraw(withdrawMemberCommand);
        return ResponseEntity.ok(DeliveryPlatformResponse.ok(null));
    }
}
