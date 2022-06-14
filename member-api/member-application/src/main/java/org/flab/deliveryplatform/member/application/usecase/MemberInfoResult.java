package org.flab.deliveryplatform.member.application.usecase;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.deliveryplatform.member.domain.Member;

@Getter
@NoArgsConstructor
public class MemberInfoResult {

    private String nickname;

    private String email;

    private String phoneNumber;

    private MemberInfoResult(String nickname, String email, String phoneNumber) {
        this.nickname = nickname;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public static MemberInfoResult from(Member member) {
        return new MemberInfoResult(
            member.getNickname(),
            member.getEmail(),
            member.getPhoneNumber()
        );
    }
}
