package org.flab.deliveryplatform.member.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import org.flab.deliveryplatform.common.auth.AuthorizationData;
import org.flab.deliveryplatform.common.auth.Token;
import org.flab.deliveryplatform.common.auth.TokenType;
import org.flab.deliveryplatform.member.application.port.AuthorizationRepository;
import org.flab.deliveryplatform.member.application.port.EncryptManager;
import org.flab.deliveryplatform.member.application.port.MemberRepository;
import org.flab.deliveryplatform.member.application.port.TokenProvider;
import org.flab.deliveryplatform.member.application.port.dto.CreateTokenCommand;
import org.flab.deliveryplatform.member.application.port.dto.LoginMemberCommand;
import org.flab.deliveryplatform.member.application.port.exception.InvalidMemberInfoException;
import org.flab.deliveryplatform.member.domain.Member;
import org.flab.deliveryplatform.member.domain.authorization.Authorization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LoginMemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private AuthorizationRepository authorizationRepository;

    @Mock
    private TokenProvider tokenProvider;

    @Mock
    private EncryptManager encryptManager;

    @InjectMocks
    private LoginMemberService loginMemberService;

    private String existingEmail = "test@test.com";
    private String notExistingEmail = existingEmail + "notExisting";

    private String validPassword = "12345678";
    private String invalidPassword = validPassword + "invalid";

    private Token token;

    @Mock
    private MemberAuthProperties authProperties;

    private MemberAuthProperties.Token tokenProperty;

    private Member member;

    private LoginMemberCommand validCommand;
    private LoginMemberCommand commandWithNotExistingEmail;
    private LoginMemberCommand commandWithInvalidPassword;

    private Authorization authorization;

    @BeforeEach
    void setUp() {
        member = new Member(1L, "nickname", existingEmail, validPassword, "010-1111-2222");

        validCommand = new LoginMemberCommand(existingEmail, validPassword);
        commandWithNotExistingEmail = new LoginMemberCommand(notExistingEmail, validPassword);
        commandWithInvalidPassword = new LoginMemberCommand(existingEmail, invalidPassword);

        token = new Token(
            TokenType.BEARER,
            UUID.randomUUID().toString()
        );

        tokenProperty = new MemberAuthProperties.Token(1_800L);

        authorization = Authorization.builder()
            .accessToken(token.getToken())
            .tokenType(token.getTokenType())
            .memberId(member.getId())
            .issueDate(LocalDateTime.now())
            .accessTokenExpiredTimeSecs(tokenProperty.getAccessTokenExpiredTimeSecs())
            .build();
    }

    @Test
    void login() {
        given(memberRepository.findByEmail(existingEmail))
            .willReturn(Optional.ofNullable(member));

        given(encryptManager.isMatch(validCommand.getPassword(), member.getPassword()))
            .willReturn(true);

        given(authProperties.getToken())
            .willReturn(tokenProperty);

        given(tokenProvider.generateToken(any(CreateTokenCommand.class)))
            .willReturn(token);

        given(authorizationRepository.save(any(Authorization.class)))
            .willReturn(authorization);

        AuthorizationData authorizationData = loginMemberService.login(validCommand);
        assertThat(authorizationData.getAccessToken()).isEqualTo(token.getToken());
    }

    @Test
    void loginWithNotExistingEmail() {
        given(memberRepository.findByEmail(notExistingEmail))
            .willThrow(new InvalidMemberInfoException());

        assertThatThrownBy(() -> loginMemberService.login(commandWithNotExistingEmail))
            .isInstanceOf(InvalidMemberInfoException.class);
    }

    @Test
    void loginWithInvalidPassword() {
        given(memberRepository.findByEmail(existingEmail))
            .willReturn(Optional.ofNullable(member));

        assertThatThrownBy(() -> loginMemberService.login(commandWithInvalidPassword))
            .isInstanceOf(InvalidMemberInfoException.class);
    }
}
