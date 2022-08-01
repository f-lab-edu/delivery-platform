package org.flab.deliveryplatform.owner.application.port;

public interface EncryptionManager {

    String encrypt(String password);

    boolean isMatch(String plainPassword, String hashedPassword);
}
