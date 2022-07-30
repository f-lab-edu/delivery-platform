package org.flab.deliveryplatform.common.auth;

public interface AuthorizationUseCase {

    AuthorizationData getAuthorizationData(String token);
}
