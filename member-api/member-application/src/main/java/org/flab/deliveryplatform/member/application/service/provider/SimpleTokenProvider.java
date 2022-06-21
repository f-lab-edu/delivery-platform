package org.flab.deliveryplatform.member.application.service.provider;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.flab.deliveryplatform.member.application.port.dto.TokenData;
import org.flab.deliveryplatform.member.domain.Member;
import org.springframework.stereotype.Component;

@Component
public class SimpleTokenProvider implements TokenProvider {

    private static final Map<String, Member> TOKEN_STORE = new ConcurrentHashMap();
    
    @Override
    public TokenData generateToken(Member member) {
        String uuidToken = UUID.randomUUID().toString();
        TOKEN_STORE.put(uuidToken, member);
        return new TokenData(uuidToken);
    }
}
