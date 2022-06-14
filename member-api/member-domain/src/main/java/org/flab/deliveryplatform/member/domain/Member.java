package org.flab.deliveryplatform.member.domain;

import lombok.Builder;
import lombok.Getter;

/**
 * @author SeunghyunYoo
 */
@Getter
public class Member {

    private Long id;

    private String nickname;

    private String email;

    private String password;

    private String phoneNumber;

    @Builder
    public Member(String nickname, String email, String password, String phoneNumber) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }
}