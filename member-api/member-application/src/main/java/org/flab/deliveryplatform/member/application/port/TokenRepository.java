package org.flab.deliveryplatform.member.application.port;

import java.util.Optional;
import org.flab.deliveryplatform.member.domain.token.Token;
import org.flab.deliveryplatform.member.domain.token.TokenKey;
import org.flab.deliveryplatform.member.domain.token.TokenValue;

public interface TokenRepository {

    Token save(Token token);

    Optional<TokenValue> findValueByKey(TokenKey key);
}
