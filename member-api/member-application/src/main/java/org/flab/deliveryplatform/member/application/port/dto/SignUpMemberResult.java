package org.flab.deliveryplatform.member.application.port.dto;

import lombok.Getter;
import org.flab.deliveryplatform.member.domain.Member;

@Getter
public class SignUpMemberResult {

    private Long id;


    public SignUpMemberResult(Long id) {
        this.id = id;
    }

    public static SignUpMemberResult from(Member member) {
        return new SignUpMemberResult(member.getId());
    }

}
