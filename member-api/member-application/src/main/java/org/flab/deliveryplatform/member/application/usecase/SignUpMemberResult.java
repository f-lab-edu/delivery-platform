package org.flab.deliveryplatform.member.application.usecase;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.deliveryplatform.member.domain.Member;

@Getter
@NoArgsConstructor
public class SignUpMemberResult {

    private Long memberId;

    private SignUpMemberResult(Long memberId) {
        this.memberId = memberId;
    }

    public static SignUpMemberResult from(Member member) {
        return new SignUpMemberResult(member.getId());
    }

}
