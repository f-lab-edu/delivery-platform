package org.flab.deliveryplatform.member.application.port;

import org.flab.deliveryplatform.member.application.port.dto.GetMemberInfoResult;
import org.flab.deliveryplatform.member.application.port.exception.InvalidMemberInfoException;

public interface GetMemberInfoUseCase {

    GetMemberInfoResult getMemberInfo(Long memberId) throws InvalidMemberInfoException;

    GetMemberInfoResult getMemberInfo(String email, String password)
        throws InvalidMemberInfoException;
}
