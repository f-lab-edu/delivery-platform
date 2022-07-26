package org.flab.deliveryplatform.owner.application.port.dto;

import lombok.Getter;

@Getter
public class SignUpOwnerCommand {

    private String nickname;

    private String email;

    private String password;

    private String phoneNumber;

    public SignUpOwnerCommand(String nickname, String email, String password, String phoneNumber) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

}
