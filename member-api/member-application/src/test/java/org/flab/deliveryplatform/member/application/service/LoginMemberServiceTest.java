package org.flab.deliveryplatform.member.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.util.Optional;
import java.util.UUID;
import org.flab.deliveryplatform.common.auth.AuthorizationData;
import org.flab.deliveryplatform.member.application.port.AuthorizationRepository;
import org.flab.deliveryplatform.member.application.port.EncryptManager;
import org.flab.deliveryplatform.member.application.port.MemberRepository;
import org.flab.deliveryplatform.member.application.port.TokenProvider;
import org.flab.deliveryplatform.member.application.port.dto.LoginMemberCommand;
import org.flab.deliveryplatform.member.application.port.exception.InvalidMemberInfoException;
import org.flab.deliveryplatform.member.domain.Member;
import org.flab.deliveryplatform.member.domain.authorization.Authorization;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LoginMemberServiceTest {

    private MemberRepository memberRepository = mock(MemberRepository.class);

    private AuthorizationRepository authorizationRepository = mock(AuthorizationRepository.class);

    private TokenProvider tokenProvider = mock(TokenProvider.class);

    private EncryptManager encryptManager = mock(EncryptManager.class);

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

    private Authorization authorization;

    @BeforeEach
    void setUp() {
        loginMemberService = new LoginMemberService(
            memberRepository, authorizationRepository, tokenProvider, encryptManager
        );

        member = new Member(1L, "nickname", existingEmail, validPassword, "010-1111-2222");

        validCommand = new LoginMemberCommand(existingEmail, validPassword);
        commandWithNotExistingEmail = new LoginMemberCommand(notExistingEmail, validPassword);
        commandWithInvalidPassword = new LoginMemberCommand(existingEmail, invalidPassword);

        authorization = Authorization.builder()
            .accessToken(accessToken)
            .memberId(member.getId())
            .build();
    }

    @Test
    void login() {
        given(memberRepository.findByEmail(existingEmail))
            .willReturn(Optional.ofNullable(member));

        given(encryptManager.isMatch(validCommand.getPassword(), member.getPassword()))
            .willReturn(true);

        given(authorizationRepository.save(any(Authorization.class)))
            .willReturn(authorization);

        AuthorizationData authorizationData = loginMemberService.login(validCommand);
        assertThat(authorizationData.getAccessToken()).isEqualTo(accessToken);
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
