package org.flab.deliveryplatform.member.application.port.dto;

import lombok.Getter;

@Getter
public class WithdrawMemberCommand {

    private String email;

    private String password;

    public WithdrawMemberCommand(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
