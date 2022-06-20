package org.flab.deliveryplatform.member.application.port.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.flab.deliveryplatform.member.domain.Member;

@Getter
@NoArgsConstructor
public class MemberData {

    private Long id;

    private String nickname;

    private String email;

    private String phoneNumber;

    private MemberData(Long id, String nickname, String email, String phoneNumber) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public static MemberData from(Member member) {
        return new MemberData(
            member.getId(),
            member.getNickname(),
            member.getEmail(),
            member.getPhoneNumber()
        );
    }
}
