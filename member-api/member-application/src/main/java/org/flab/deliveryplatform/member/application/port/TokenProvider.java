package org.flab.deliveryplatform.member.application.port;

import org.flab.deliveryplatform.common.auth.Token;
import org.flab.deliveryplatform.member.application.port.dto.CreateTokenCommand;


public interface TokenProvider {

    Token generateToken(CreateTokenCommand command);
}
