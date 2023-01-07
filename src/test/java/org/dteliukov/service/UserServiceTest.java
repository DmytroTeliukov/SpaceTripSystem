package org.dteliukov.service;

import org.dteliukov.dao.DaoRepository;
import org.dteliukov.dao.TypeDao;
import org.dteliukov.dao.UserDao;
import org.dteliukov.exception.AuthorizationException;
import org.dteliukov.exception.RegistrationException;
import org.dteliukov.model.authorization.UnauthorizedUser;
import org.dteliukov.model.domain.UserProfile;
import org.dteliukov.security.SecurityPassword;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {
    static UserProfile user;
    static UserService service;

    @BeforeAll
    static void setUser() throws RegistrationException {
        service = new UserService();
        user = new UserProfile("Lastname", "Firstname", "test@gmail.com",
                "0987654321", "1", "CLIENT", "ACTIVE");
        service.registration(user, "1");
        user.password("1");
    }

    @Test
    void authorizeSuccessfully() throws AuthorizationException {
        var authorizedUser = service.authorize(user.getEmail(), user.getPassword());

        Assertions.assertEquals(user.getEmail(), authorizedUser.email());
    }

    @Test
    void authorizeFailureWithNotExistingUser() {
        Assertions.assertThrows(AuthorizationException.class,
                () -> service.authorize("-", "-"));
    }

    @Test
    void authorizeFailureWithNotCorrectPassword() {
        Assertions.assertThrows(AuthorizationException.class,
                () -> service.authorize(user.getEmail(), "-"));
    }

    @Test
    void getUserProfile() {
        var retrievedUser = service.getUserProfileByEmail("test@gmail.com");

        Assertions.assertEquals(user.getEmail(), retrievedUser.getEmail());
    }


    @Test
    void updateUser() throws Exception {
        var retrievedUser = service.getUserProfileByEmail("test@gmail.com");
        user.firstname("1");
        service.updateUserProfile(user);
        var retrievedUpdatedUser = service.getUserProfileByEmail("test@gmail.com");


        Assertions.assertNotEquals(retrievedUser, retrievedUpdatedUser);
        Assertions.assertEquals("1", retrievedUpdatedUser.getFirstname());
    }

    @Test
    void setPassword() throws Exception {
        var retrievedUser = service.getUserProfileByEmail("test@gmail.com");
        user.password("2");
        service.updatePassword(user, "2");
        var retrievedUpdatedUser = service.getUserProfileByEmail("test@gmail.com");


        Assertions.assertNotEquals(retrievedUser, retrievedUpdatedUser);
        Assertions.assertTrue(SecurityPassword.verifyUserPassword(user.getEmail(), "2",
                retrievedUpdatedUser.getPassword()));
    }

    @Test
    void readUsers() {
        var users = service.readUsers();

        Assertions.assertNotEquals(0, users.size());
    }

    @Test
    void setUserStatus() {
        service.setStatus(user.getEmail(), "BANNED");
        var retrievedUser = service.getUserProfileByEmail("test@gmail.com");

        Assertions.assertNotEquals(user, retrievedUser);
        Assertions.assertEquals("BANNED", retrievedUser.getStatus());
    }



    @AfterAll
    static void deleteUser() {
        service.deleteUser(user.getEmail());
    }
}