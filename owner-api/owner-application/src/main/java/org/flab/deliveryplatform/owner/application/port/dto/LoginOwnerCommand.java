package org.flab.deliveryplatform.owner.application.port.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginOwnerCommand {

    private String email;

    private String password;
}

