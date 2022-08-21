package org.flab.deliveryplatform.common.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Token {

    TokenType tokenType;

    String token;
}
