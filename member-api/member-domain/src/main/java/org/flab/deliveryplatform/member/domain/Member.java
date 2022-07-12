package org.flab.deliveryplatform.member.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Member {

    private Long id;

    private String nickname;

    private String email;

    private String password;

    private String phoneNumber;

    @Builder
    public Member(Long id, String nickname, String email, String password, String phoneNumber) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public boolean authenticate(String password) {
        return this.password.equals(password);
    }
}
