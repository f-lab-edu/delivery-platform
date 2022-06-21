package org.flab.deliveryplatform.member.application.port.dto;

import lombok.Getter;

@Getter
public class CreateTokenCommand {

    private Long memberId;

    public CreateTokenCommand(Long memberId) {
        this.memberId = memberId;
    }
}
