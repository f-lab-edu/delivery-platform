package org.flab.deliveryplatform.member.application.service;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.member.application.port.EncryptManager;
import org.flab.deliveryplatform.member.application.port.MemberRepository;
import org.flab.deliveryplatform.member.application.port.WithdrawMemberUseCase;
import org.flab.deliveryplatform.member.application.port.dto.WithdrawMemberCommand;
import org.flab.deliveryplatform.member.application.port.exception.InvalidMemberInfoException;
import org.flab.deliveryplatform.member.domain.Member;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WithdrawMemberService implements WithdrawMemberUseCase {

    private final MemberRepository memberRepository;

    private final EncryptManager encryptManager;

    @Override
    public void withdraw(WithdrawMemberCommand withdrawMemberCommand) {
        Member member = memberRepository.findByEmail(withdrawMemberCommand.getEmail())
            .orElseThrow(InvalidMemberInfoException::new);

        if (!encryptManager.isMatch(withdrawMemberCommand.getPassword(), member.getPassword())) {
            throw new InvalidMemberInfoException();
        }

        memberRepository.delete(member);
    }
}
