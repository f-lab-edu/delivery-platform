package org.flab.deliveryplatform.member.application.service.provider;

import java.util.UUID;
import org.flab.deliveryplatform.member.application.port.dto.CreateTokenCommand;
import org.springframework.stereotype.Component;

@Component
public class SimpleTokenProvider implements TokenProvider {

    @Override
    public String generateToken(CreateTokenCommand command) {
        return UUID.randomUUID().toString();
    }
}
