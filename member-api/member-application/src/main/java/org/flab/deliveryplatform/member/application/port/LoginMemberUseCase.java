package org.flab.deliveryplatform.member.application.port;

import org.flab.deliveryplatform.common.auth.AuthorizationData;
import org.flab.deliveryplatform.member.application.port.dto.LoginMemberCommand;

public interface LoginMemberUseCase {

    AuthorizationData login(LoginMemberCommand command);
}
