package org.flab.deliveryplatform.member.application.port.dto;

import lombok.Getter;

@Getter
public class SignUpMemberCommand {

    private String nickname;

    private String email;

    private String password;

    private String phoneNumber;

    public SignUpMemberCommand(String nickname, String email, String password, String phoneNumber) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
}
