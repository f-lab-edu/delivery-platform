package org.flab.deliveryplatform.member.infrastructure.token;

import java.util.UUID;
import org.flab.deliveryplatform.common.auth.Token;
import org.flab.deliveryplatform.common.auth.TokenType;
import org.flab.deliveryplatform.member.application.port.TokenProvider;
import org.flab.deliveryplatform.member.application.port.dto.CreateTokenCommand;
import org.springframework.stereotype.Component;

@Component
public class SimpleTokenProvider implements TokenProvider {

    @Override
    public Token generateToken(CreateTokenCommand command) {
        return new Token(TokenType.BEARER, UUID.randomUUID().toString());
    }
}
