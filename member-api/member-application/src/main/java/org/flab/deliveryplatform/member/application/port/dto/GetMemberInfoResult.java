package org.flab.deliveryplatform.member.application.port.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.deliveryplatform.member.domain.Member;

@Getter
@NoArgsConstructor
public class GetMemberInfoResult {

    private Long id;

    private String nickname;

    private String email;

    private String phoneNumber;

    private GetMemberInfoResult(Long id, String nickname, String email, String phoneNumber) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public static GetMemberInfoResult from(Member member) {
        return new GetMemberInfoResult(
            member.getId(),
            member.getNickname(),
            member.getEmail(),
            member.getPhoneNumber()
        );
    }
}
