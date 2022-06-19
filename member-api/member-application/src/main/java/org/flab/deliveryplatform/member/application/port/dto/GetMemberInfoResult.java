package org.flab.deliveryplatform.member.application.port.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.deliveryplatform.member.domain.Member;

@Getter
@NoArgsConstructor
public class GetMemberInfoResult {

    private String nickname;

    private String email;

    private String phoneNumber;

    private GetMemberInfoResult(String nickname, String email, String phoneNumber) {
        this.nickname = nickname;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public static GetMemberInfoResult from(Member member) {
        return new GetMemberInfoResult(
            member.getNickname(),
            member.getEmail(),
            member.getPhoneNumber()
        );
    }
}
