package org.flab.deliveryplatform.owner.application.port;

import org.flab.deliveryplatform.common.auth.AuthorizationData;
import org.flab.deliveryplatform.owner.application.port.dto.LoginOwnerCommand;

public interface LoginOwnerUseCase {

    AuthorizationData login(LoginOwnerCommand loginOwnerCommand);
}
