package org.flab.deliveryplatform.owner.application.port;

import org.flab.deliveryplatform.common.auth.Token;

public interface TokenProvider {

    Token generateToken();
}
