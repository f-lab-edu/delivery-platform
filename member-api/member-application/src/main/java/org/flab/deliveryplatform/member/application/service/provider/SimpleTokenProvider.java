package org.flab.deliveryplatform.member.application.service.provider;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.flab.deliveryplatform.member.application.port.dto.CreateTokenCommand;
import org.flab.deliveryplatform.member.application.port.dto.TokenData;
import org.flab.deliveryplatform.member.application.port.exception.InvalidTokenException;
import org.springframework.stereotype.Component;

@Component
public class SimpleTokenProvider implements TokenProvider {

    private static final Map<String, Long> TOKEN_STORE = new ConcurrentHashMap();
    
    @Override
    public TokenData generateToken(CreateTokenCommand command) {
        String uuidToken = UUID.randomUUID().toString();
        TOKEN_STORE.put(uuidToken, command.getMemberId());
        return new TokenData(uuidToken);
    }

    @Override
    public Long parseToken(String accessToken) {
        Long memberId = TOKEN_STORE.get(accessToken);
        if (memberId == null) {
            throw new InvalidTokenException();
        }

        return memberId;
    }
}
