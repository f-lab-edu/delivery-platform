package org.flab.deliveryplatform.member.application.port.dto;

import lombok.Getter;

@Getter
public class LoginMemberCommand {

    private String email;

    private String password;

    public LoginMemberCommand(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
