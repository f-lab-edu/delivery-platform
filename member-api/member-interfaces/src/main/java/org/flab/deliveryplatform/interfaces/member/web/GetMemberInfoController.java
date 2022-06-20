package org.flab.deliveryplatform.interfaces.member.web;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.common.web.DeliveryPlatformResponse;
import org.flab.deliveryplatform.member.application.port.GetMemberInfoUseCase;
import org.flab.deliveryplatform.member.application.port.dto.GetMemberInfoResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class GetMemberInfoController {

    private final GetMemberInfoUseCase getMemberInfoUseCase;

    @GetMapping("/{memberId}")
    public ResponseEntity<DeliveryPlatformResponse<GetMemberInfoResult>> memberInfo(
        @PathVariable Long memberId) {
        return ResponseEntity.ok(DeliveryPlatformResponse.ok(
            getMemberInfoUseCase.getMemberInfo(memberId)
        ));
    }

}
