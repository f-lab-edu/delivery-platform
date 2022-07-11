package org.flab.deliveryplatform.member.application.service.provider;

import org.flab.deliveryplatform.member.application.port.dto.CreateTokenCommand;


public interface TokenProvider {

    String generateToken(CreateTokenCommand command);

    Long parseToken(String accessToken);
}
