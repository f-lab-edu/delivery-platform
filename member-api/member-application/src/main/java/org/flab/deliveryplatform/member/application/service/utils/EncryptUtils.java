package org.flab.deliveryplatform.member.application.service.utils;

public interface EncryptUtils {

    String encrypt(String password);

    boolean isMatch(String password, String hashedPassword);
}
