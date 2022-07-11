package org.flab.deliveryplatform.member.application.service.provider;

import static org.assertj.core.api.Assertions.assertThat;

import org.flab.deliveryplatform.member.application.port.dto.CreateTokenCommand;
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
        String token = tokenProvider.generateToken(createTokenCommand);

        assertThat(token).isNotBlank();
        assertThat(token).contains("-");
    }

    // TODO: parseToken 기능 재구현
    // @Test
    // void parseToken() {
    //     String token = tokenProvider.generateToken(createTokenCommand);
    //
    //     Long memberId = tokenProvider.parseToken(token);
    //
    //     assertThat(memberId).isEqualTo(createTokenCommand.getMemberId());
    // }

    // @Test
    // void parseTokenWithInvalidAccessToken() {
    //     String token = tokenProvider.generateToken(createTokenCommand);
    //
    //     assertThatThrownBy(() -> tokenProvider.parseToken(token + "INVALID"))
    //         .isInstanceOf(InvalidTokenException.class);
    // }
}
