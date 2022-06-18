package org.flab.deliveryplatform.member.application.port.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.deliveryplatform.member.domain.Member;

@Getter
@NoArgsConstructor
public class SignUpMemberResult {

    private Long id;

    private SignUpMemberResult(Long id) {
        this.id = id;
    }

    public static SignUpMemberResult from(Member member) {
        return new SignUpMemberResult(member.getId());
    }

}
