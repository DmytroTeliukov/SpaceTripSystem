package org.dteliukov.dao.mysql;

import org.dteliukov.dao.DaoRepository;
import org.dteliukov.dao.TypeDao;
import org.dteliukov.dao.UserDao;
import org.dteliukov.model.authorization.UnauthorizedUser;
import org.dteliukov.model.domain.UserProfile;
import org.dteliukov.security.SecurityPassword;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

class UserMySqlDaoTest {
    static UserProfile user;
    static UserDao dao;

    @BeforeAll
    static void setUser() {
        dao = DaoRepository.getDao(TypeDao.MySQL).getUserDao();
        user = new UserProfile("Smith", "Luis", "admin@space_trip.com",
                "0987654321", SecurityPassword.generateSecurePassword("admin@space_trip.com", "12345"),
                "ADMIN", "ACTIVE");
        dao.registration(user);
    }

    @Test
    void authorize() {
        var unauthorizedUser = new UnauthorizedUser(user.getEmail(), user.getPassword());
        var authorizedUser = dao.authorization(unauthorizedUser);

        Assertions.assertNotEquals(Optional.empty(), authorizedUser);
        Assertions.assertEquals(unauthorizedUser.email(), authorizedUser.get().email());
    }

    @Test
    void getUserProfile() {
        var retrievedUser = dao.getUserProfile("test@gmail.com");

        Assertions.assertEquals(user, retrievedUser.get());
    }

    @Test
    void getUser() {
        var retrievedUser = dao.getUser("test@gmail.com").get();

        Assertions.assertEquals(user.getLastname(), retrievedUser.getLastname());
        Assertions.assertEquals(user.getEmail(), retrievedUser.getEmail());
        Assertions.assertEquals(user.getPhone(), retrievedUser.getPhone());
    }

    @Test
    void getEmptyUserProfile() {
        var retrievedUser = dao.getUserProfile("test2@gmail.com");


        Assertions.assertThrows(NoSuchElementException.class, retrievedUser::get);
    }

    @Test
    void getEmptyUser() {
        var retrievedUser = dao.getUser("test2@gmail.com");

        Assertions.assertThrows(NoSuchElementException.class, retrievedUser::get);
    }

    @Test
    void updateUser() {
        var retrievedUser = dao.getUserProfile("test@gmail.com");
        user.firstname("1");
        dao.updateUser(user);
        var retrievedUpdatedUser = dao.getUserProfile("test@gmail.com");


        Assertions.assertNotEquals(retrievedUser.get(), retrievedUpdatedUser.get());
        Assertions.assertEquals("1", retrievedUpdatedUser.get().getFirstname());
    }

    @Test
    void readUsers() {
        var users = dao.readUsers();

        Assertions.assertNotEquals(0, users.size());
    }

    @Test
    void setUserStatus() {
        dao.setUserStatus(user.getEmail(), "BANNED");
        var retrievedUser = dao.getUserProfile("test@gmail.com");

        Assertions.assertNotEquals(user, retrievedUser.get());
        Assertions.assertEquals("BANNED", retrievedUser.get().getStatus());
    }


    @AfterAll
    static void deleteUser() {
        dao.deleteUser(user.getEmail());
    }
}