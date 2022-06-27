package org.flab.deliveryplatform.member.infrastructure.persistence;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.flab.deliveryplatform.member.domain.token.Token;
import org.flab.deliveryplatform.member.domain.token.TokenKey;
import org.flab.deliveryplatform.member.domain.token.TokenValue;
import org.springframework.stereotype.Repository;

@Repository
public class MemoryTokenRepository {

    private static final Map<TokenKey, TokenValue> TOKEN_STORE = new ConcurrentHashMap();

    public Token save(Token token) {
        TOKEN_STORE.put(token.getTokenKey(), token.getTokenValue());
        return token;
    }

    public Optional<TokenValue> findByKey(TokenKey key) {
        return Optional.ofNullable(TOKEN_STORE.get(key));
    }
}
