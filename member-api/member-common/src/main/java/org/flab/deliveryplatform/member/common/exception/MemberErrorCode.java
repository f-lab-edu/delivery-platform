package org.flab.deliveryplatform.member.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.flab.deliveryplatform.common.exception.DeliveryPlatformErrorCode;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements DeliveryPlatformErrorCode {
    DUPLICATED_EMAIL("중복된 이메일입니다."),
    INVALID_MEMBER_INFO("잘못된 회원 정보입니다.");

    private String message;
}
