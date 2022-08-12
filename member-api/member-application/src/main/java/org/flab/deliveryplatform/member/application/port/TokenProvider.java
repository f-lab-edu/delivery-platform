package org.flab.deliveryplatform.member.application.port;

import org.flab.deliveryplatform.member.application.port.dto.CreateTokenCommand;


public interface TokenProvider {

    String generateToken(CreateTokenCommand command);
}
