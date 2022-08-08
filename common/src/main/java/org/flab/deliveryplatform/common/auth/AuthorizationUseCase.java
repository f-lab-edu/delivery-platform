package org.flab.deliveryplatform.common.auth;

public interface AuthorizationUseCase {

    UserType getUserType();

    AuthorizationData getAuthorizationData(String token);
}
