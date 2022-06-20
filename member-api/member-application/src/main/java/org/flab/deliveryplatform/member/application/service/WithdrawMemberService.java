package org.flab.deliveryplatform.member.application.service;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.member.application.port.MemberPersistencePort;
import org.flab.deliveryplatform.member.application.port.WithdrawMemberUseCase;
import org.flab.deliveryplatform.member.application.port.dto.WithdrawMemberCommand;
import org.flab.deliveryplatform.member.application.port.exception.InvalidMemberInfoException;
import org.flab.deliveryplatform.member.domain.Member;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WithdrawMemberService implements WithdrawMemberUseCase {

    private final MemberPersistencePort memberPersistencePort;

    @Override
    public void withdraw(WithdrawMemberCommand withdrawMemberCommand) {
        Member member = memberPersistencePort.findByEmailAndPassword(
                withdrawMemberCommand.getEmail(),
                withdrawMemberCommand.getPassword())
            .orElseThrow(() -> new InvalidMemberInfoException("잘못된 회원 정보입니다."));

        memberPersistencePort.delete(member);
    }
}
