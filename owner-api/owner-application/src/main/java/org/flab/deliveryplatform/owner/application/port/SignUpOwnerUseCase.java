package org.flab.deliveryplatform.owner.application.port;

import org.flab.deliveryplatform.owner.application.port.dto.SignUpOwnerCommand;
import org.flab.deliveryplatform.owner.application.port.dto.SignUpOwnerResult;

public interface SignUpOwnerUseCase {

    SignUpOwnerResult signUp(SignUpOwnerCommand signUpOwnerCommand);
}
