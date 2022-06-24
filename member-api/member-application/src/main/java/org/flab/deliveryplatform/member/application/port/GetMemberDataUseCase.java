package org.flab.deliveryplatform.member.application.port;

import org.flab.deliveryplatform.member.application.port.dto.MemberData;
import org.flab.deliveryplatform.member.application.port.exception.InvalidMemberInfoException;

public interface GetMemberDataUseCase {

    MemberData getMemberData(Long memberId) throws InvalidMemberInfoException;

    MemberData getMemberData(String email, String password) throws InvalidMemberInfoException;
}
