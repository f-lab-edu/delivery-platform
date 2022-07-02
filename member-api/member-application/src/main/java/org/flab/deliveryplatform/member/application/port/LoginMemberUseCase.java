package org.flab.deliveryplatform.member.application.port;

import org.flab.deliveryplatform.member.application.port.dto.LoginMemberCommand;
import org.flab.deliveryplatform.member.application.port.dto.TokenData;

public interface LoginMemberUseCase {

    TokenData login(LoginMemberCommand command);
}
