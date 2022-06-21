package org.flab.deliveryplatform.member.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

import java.util.Optional;
import java.util.UUID;
import org.flab.deliveryplatform.member.application.port.MemberPersistencePort;
import org.flab.deliveryplatform.member.application.port.dto.LoginMemberCommand;
import org.flab.deliveryplatform.member.application.port.dto.TokenData;
import org.flab.deliveryplatform.member.application.port.exception.InvalidMemberInfoException;
import org.flab.deliveryplatform.member.application.service.provider.TokenProvider;
import org.flab.deliveryplatform.member.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LoginMemberServiceTest {

    @Mock
    private MemberPersistencePort memberPersistencePort;

    @Mock
    private TokenProvider tokenProvider;

    @InjectMocks
    private LoginMemberService loginMemberService;

    private String existingEmail = "test@test.com";
    private String notExistingEmail = existingEmail + "notExisting";

    private String validPassword = "12345678";
    private String invalidPassword = validPassword + "invalid";

    private String accessToken = UUID.randomUUID().toString();

    private Member member;

    private LoginMemberCommand validCommand;
    private LoginMemberCommand commandWithNotExistingEmail;
    private LoginMemberCommand commandWithInvalidPassword;

    @BeforeEach
    void setUp() {
        member = new Member(1L, "nickname", existingEmail, validPassword, "010-1111-2222");

        validCommand = new LoginMemberCommand(existingEmail, validPassword);
        commandWithNotExistingEmail = new LoginMemberCommand(notExistingEmail, validPassword);
        commandWithInvalidPassword = new LoginMemberCommand(existingEmail, invalidPassword);
    }

    @Test
    void login() {
        given(memberPersistencePort.findByEmail(existingEmail))
            .willReturn(Optional.ofNullable(member));

        given(tokenProvider.generateToken(member))
            .willReturn(new TokenData(accessToken));

        TokenData tokenData = loginMemberService.login(validCommand);
        assertThat(tokenData.getAccessToken()).isEqualTo(accessToken);
    }

    @Test
    void loginWithNotExistingEmail() {
        given(memberPersistencePort.findByEmail(notExistingEmail))
            .willThrow(new InvalidMemberInfoException());

        assertThatThrownBy(() -> loginMemberService.login(commandWithNotExistingEmail))
            .isInstanceOf(InvalidMemberInfoException.class);
    }

    @Test
    void loginWithInvalidPassword() {
        given(memberPersistencePort.findByEmail(existingEmail))
            .willReturn(Optional.ofNullable(member));

        assertThatThrownBy(() -> loginMemberService.login(commandWithInvalidPassword))
            .isInstanceOf(InvalidMemberInfoException.class);
    }
}
