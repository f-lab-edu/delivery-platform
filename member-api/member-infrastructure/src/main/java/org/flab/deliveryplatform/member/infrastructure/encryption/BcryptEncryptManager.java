package org.flab.deliveryplatform.member.infrastructure.encryption;

import org.flab.deliveryplatform.member.application.port.EncryptManager;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class BcryptEncryptManager implements EncryptManager {

    public String encrypt(String password) {
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(password, salt);
    }

    public boolean isMatch(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
