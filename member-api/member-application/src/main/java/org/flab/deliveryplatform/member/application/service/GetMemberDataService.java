package org.flab.deliveryplatform.member.application.service;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.member.application.port.GetMemberDataUseCase;
import org.flab.deliveryplatform.member.application.port.MemberRepository;
import org.flab.deliveryplatform.member.application.port.dto.MemberData;
import org.flab.deliveryplatform.member.application.port.exception.InvalidMemberInfoException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class GetMemberDataService implements GetMemberDataUseCase {

    private final MemberRepository memberRepository;

    @Override
    public MemberData getMemberData(Long memberId) throws InvalidMemberInfoException {
        return MemberData.from(
            memberRepository.findById(memberId)
                .orElseThrow(InvalidMemberInfoException::new)
        );
    }

    @Override
    public MemberData getMemberData(String email, String password)
        throws InvalidMemberInfoException {
        return MemberData.from(
            memberRepository.findByEmailAndPassword(email, password)
                .orElseThrow(InvalidMemberInfoException::new)
        );
    }
}
