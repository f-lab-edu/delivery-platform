package org.flab.deliveryplatform.common.auth;

import java.security.Principal;
import lombok.Getter;

@Getter
public class UserPrinciple implements Principal {

    private String name;

    private UserType userType;

    private Long userId;

    public UserPrinciple(UserType userType, Long userId) {
        this.name = userType.name() + ":" + userId;
        this.userType = userType;
        this.userId = userId;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
