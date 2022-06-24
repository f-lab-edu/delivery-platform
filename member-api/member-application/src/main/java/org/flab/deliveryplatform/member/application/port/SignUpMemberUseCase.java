package org.flab.deliveryplatform.member.application.port;

import org.flab.deliveryplatform.member.application.port.dto.SignUpMemberCommand;
import org.flab.deliveryplatform.member.application.port.dto.SignUpMemberResult;
import org.flab.deliveryplatform.member.application.port.exception.DuplicatedEmailException;

public interface SignUpMemberUseCase {

    SignUpMemberResult signUp(SignUpMemberCommand signUpMemberCommand)
        throws DuplicatedEmailException;
}
