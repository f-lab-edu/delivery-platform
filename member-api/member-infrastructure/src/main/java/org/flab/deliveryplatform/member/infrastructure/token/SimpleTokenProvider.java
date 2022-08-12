package org.flab.deliveryplatform.member.infrastructure.token;

import java.util.UUID;
import org.flab.deliveryplatform.member.application.port.TokenProvider;
import org.flab.deliveryplatform.member.application.port.dto.CreateTokenCommand;
import org.springframework.stereotype.Component;

@Component
public class SimpleTokenProvider implements TokenProvider {

    @Override
    public String generateToken(CreateTokenCommand command) {
        return UUID.randomUUID().toString();
    }
}
