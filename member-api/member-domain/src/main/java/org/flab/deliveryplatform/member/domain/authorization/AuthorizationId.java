package org.flab.deliveryplatform.member.domain.authorization;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(of = {"id"})
@Getter
public class AuthorizationId {

    private final String id;

    public AuthorizationId(String id) {
        this.id = id;
    }
}
