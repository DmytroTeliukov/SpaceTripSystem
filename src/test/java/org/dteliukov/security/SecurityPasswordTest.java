package org.dteliukov.security;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class SecurityPasswordTest {

    static String salt;
    static final int SALT_LENGTH = 256;

    @BeforeAll
    static void setUp() {
        salt = SecurityPassword.getSalt(SALT_LENGTH);
    }

    @Test
    void successfulVerifying() {
        String firstPassword = "first";
        String secondPassword = "second";
        String thirdPassword = "third";

        String firstPasswordResult = SecurityPassword.generateSecurePassword(firstPassword, salt);
        String secondPasswordResult = SecurityPassword.generateSecurePassword(secondPassword, salt);
        String thirdPasswordResult = SecurityPassword.generateSecurePassword(thirdPassword, salt);

        Assertions.assertTrue(SecurityPassword.verifyUserPassword(firstPassword, firstPasswordResult, salt));
        Assertions.assertTrue(SecurityPassword.verifyUserPassword(secondPassword, secondPasswordResult, salt));
        Assertions.assertTrue(SecurityPassword.verifyUserPassword(thirdPassword, thirdPasswordResult, salt));
    }

}