package org.flab.deliveryplatform.owner.application.port.dto;

import lombok.Getter;

@Getter
public class SignUpOwnerResult {

    private Long id;

    public SignUpOwnerResult(Long id) {
        this.id = id;
    }
}
