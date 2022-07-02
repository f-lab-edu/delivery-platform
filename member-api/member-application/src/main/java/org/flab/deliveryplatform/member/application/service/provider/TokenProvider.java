package org.flab.deliveryplatform.member.application.service.provider;

import org.flab.deliveryplatform.member.application.port.dto.CreateTokenCommand;
import org.flab.deliveryplatform.member.application.port.dto.TokenData;

public interface TokenProvider {

    TokenData generateToken(CreateTokenCommand command);
}
