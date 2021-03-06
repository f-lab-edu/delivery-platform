package org.flab.deliveryplatform.member.interfaces.web;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.web.dto.DeliveryPlatformResponse;
import org.flab.deliveryplatform.member.application.port.GetMemberDataUseCase;
import org.flab.deliveryplatform.member.application.port.dto.MemberData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@MemberRestController
@RequiredArgsConstructor
public class GetMemberDataController {

    private final GetMemberDataUseCase getMemberDataUseCase;

    @GetMapping("/{memberId}")
    public ResponseEntity<DeliveryPlatformResponse<MemberData>> memberInfo(
        @PathVariable Long memberId) {
        return ResponseEntity.ok(DeliveryPlatformResponse.ok(
            getMemberDataUseCase.getMemberData(memberId)
        ));
    }

}
