package org.flab.deliveryplatform.member.application.port;

import org.flab.deliveryplatform.member.application.port.dto.WithdrawMemberCommand;
import org.flab.deliveryplatform.member.application.port.exception.InvalidMemberInfoException;

public interface WithdrawMemberUseCase {

    void withdraw(WithdrawMemberCommand withdrawMemberCommand) throws InvalidMemberInfoException;
}
