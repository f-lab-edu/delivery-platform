package org.flab.deliveryplatform.member.infrastructure.persistence;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.member.application.port.TokenRepository;
import org.flab.deliveryplatform.member.domain.token.Token;
import org.flab.deliveryplatform.member.domain.token.TokenKey;
import org.flab.deliveryplatform.member.domain.token.TokenValue;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class TokenAdapter implements TokenRepository {

    private final MemoryTokenRepository memoryTokenRepository;

    public Token save(Token token) {
        return memoryTokenRepository.save(token);
    }

    public Optional<TokenValue> findValueByKey(TokenKey key) {
        return memoryTokenRepository.findByKey(key);
    }
}
