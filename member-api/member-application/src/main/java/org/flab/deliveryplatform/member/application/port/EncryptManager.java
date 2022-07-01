package org.flab.deliveryplatform.member.application.port;

public interface EncryptManager {

    String encrypt(String password);

    boolean isMatch(String password, String hashedPassword);
}
