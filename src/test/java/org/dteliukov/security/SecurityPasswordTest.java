package org.dteliukov.security;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class SecurityPasswordTest {



    @Test
    void successfulVerifying() {
        String firstPassword = "first";
        String secondPassword = "second";
        String thirdPassword = "third";

        String firstPasswordResult = SecurityPassword.generateSecurePassword(firstPassword);
        String secondPasswordResult = SecurityPassword.generateSecurePassword(secondPassword);
        String thirdPasswordResult = SecurityPassword.generateSecurePassword(thirdPassword);


        Assertions.assertTrue(SecurityPassword.verifyUserPassword(firstPassword, firstPasswordResult));
        Assertions.assertTrue(SecurityPassword.verifyUserPassword(secondPassword, secondPasswordResult));
        Assertions.assertTrue(SecurityPassword.verifyUserPassword(thirdPassword, thirdPasswordResult));
    }

}