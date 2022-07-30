package org.flab.deliveryplatform.owner.infrastructure.token;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.owner.application.port.TokenProvider;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RandomTokenProvider implements TokenProvider {

    @Override
    public String generateToken() {
        return UUID.randomUUID().toString();
    }
}
