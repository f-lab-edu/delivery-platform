package org.flab.deliveryplatform.member.application.service.provider;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.flab.deliveryplatform.member.application.port.dto.CreateTokenCommand;
import org.flab.deliveryplatform.member.application.port.dto.TokenData;
import org.flab.deliveryplatform.member.application.port.exception.InvalidTokenException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SimpleTokenProviderTest {

    private TokenProvider tokenProvider;

    private CreateTokenCommand createTokenCommand;

    @BeforeEach
    void setUp() {
        tokenProvider = new SimpleTokenProvider();

        createTokenCommand = new CreateTokenCommand(1L);
    }

    @Test
    void generateToken() {
        TokenData tokenData = tokenProvider.generateToken(createTokenCommand);

        assertThat(tokenData.getAccessToken()).isNotBlank();
        assertThat(tokenData.getAccessToken()).contains("-");
    }

    @Test
    void parseToken() {
        TokenData tokenData = tokenProvider.generateToken(createTokenCommand);

        Long memberId = tokenProvider.parseToken(tokenData.getAccessToken());

        assertThat(memberId).isEqualTo(createTokenCommand.getMemberId());
    }

    @Test
    void parseTokenWithInvalidAccessToken() {
        TokenData tokenData = tokenProvider.generateToken(createTokenCommand);

        assertThatThrownBy(() -> tokenProvider.parseToken(tokenData.getAccessToken() + "INVALID"))
            .isInstanceOf(InvalidTokenException.class);
    }
}
