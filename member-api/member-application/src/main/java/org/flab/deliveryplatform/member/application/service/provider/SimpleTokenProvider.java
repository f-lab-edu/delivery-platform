package org.flab.deliveryplatform.member.application.service.provider;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.member.application.port.TokenRepository;
import org.flab.deliveryplatform.member.application.port.dto.CreateTokenCommand;
import org.flab.deliveryplatform.member.application.port.dto.TokenData;
import org.flab.deliveryplatform.member.domain.token.Token;
import org.flab.deliveryplatform.member.domain.token.TokenKey;
import org.flab.deliveryplatform.member.domain.token.TokenValue;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SimpleTokenProvider implements TokenProvider {

    private final TokenRepository tokenRepository;

    @Override
    public TokenData generateToken(CreateTokenCommand command) {
        TokenKey key = new TokenKey(UUID.randomUUID().toString());
        TokenValue value = new TokenValue(command.getMemberId());
        Token token = tokenRepository.save(new Token(key, value));

        return new TokenData(token.getTokenKey().getKey());
    }
}
