package org.flab.deliveryplatform.member.application.service.provider;

import org.flab.deliveryplatform.member.application.port.dto.TokenData;
import org.flab.deliveryplatform.member.domain.Member;

public interface TokenProvider {

    TokenData generateToken(Member member);
}
