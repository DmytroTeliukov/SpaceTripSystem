package org.dteliukov.security;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SecurityPasswordTest {

    @Test
    void successfulVerifying() {
        String firstPassword = "first";
        String firstEmail = "first@gmail.com";
        String secondPassword = "second";
        String secondEmail = "second@gmail.com";
        String thirdPassword = "third";
        String thirdEmail = "third@gmail.com";


        String firstPasswordResult = SecurityPassword.generateSecurePassword(firstEmail, firstPassword);
        String secondPasswordResult = SecurityPassword.generateSecurePassword(secondEmail, secondPassword);
        String thirdPasswordResult = SecurityPassword.generateSecurePassword(thirdEmail, thirdPassword);


        Assertions.assertTrue(SecurityPassword.verifyUserPassword(firstEmail, firstPassword, firstPasswordResult));
        Assertions.assertTrue(SecurityPassword.verifyUserPassword(secondEmail, secondPassword, secondPasswordResult));
        Assertions.assertTrue(SecurityPassword.verifyUserPassword(thirdEmail, thirdPassword, thirdPasswordResult));
    }

}