package org.flab.deliveryplatform.interfaces.member.web;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformResponse;
import org.flab.deliveryplatform.member.application.port.WithdrawMemberUseCase;
import org.flab.deliveryplatform.member.application.port.dto.WithdrawMemberCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class WithdrawMemberController {

    private final WithdrawMemberUseCase withdrawMemberUseCase;

    @DeleteMapping("/withdraw")
    public ResponseEntity<DeliveryPlatformResponse<Object>> withdraw(
        @RequestBody WithdrawMemberCommand withdrawMemberCommand) {
        withdrawMemberUseCase.withdraw(withdrawMemberCommand);
        return ResponseEntity.ok(DeliveryPlatformResponse.ok(null));
    }
}
