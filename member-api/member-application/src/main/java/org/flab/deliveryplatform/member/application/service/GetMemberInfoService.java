package org.flab.deliveryplatform.member.application.service;

import lombok.RequiredArgsConstructor;
import org.flab.deliveryplatform.member.application.port.GetMemberInfoUseCase;
import org.flab.deliveryplatform.member.application.port.MemberPersistencePort;
import org.flab.deliveryplatform.member.application.port.dto.GetMemberInfoResult;
import org.flab.deliveryplatform.member.application.port.exception.InvalidMemberInfoException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class GetMemberInfoService implements GetMemberInfoUseCase {

    private final MemberPersistencePort memberPersistencePort;

    @Override
    public GetMemberInfoResult getMemberInfo(Long memberId) throws InvalidMemberInfoException {
        return GetMemberInfoResult.from(
            memberPersistencePort.findById(memberId)
                .orElseThrow(InvalidMemberInfoException::new)
        );
    }

    @Override
    public GetMemberInfoResult getMemberInfo(String email, String password)
        throws InvalidMemberInfoException {
        return GetMemberInfoResult.from(
            memberPersistencePort.findByEmailAndPassword(email, password)
                .orElseThrow(InvalidMemberInfoException::new)
        );
    }
}
