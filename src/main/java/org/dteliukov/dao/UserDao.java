package org.dteliukov.dao;

import org.dteliukov.model.authorization.AuthorizedUser;
import org.dteliukov.model.authorization.UnauthorizedUser;
import org.dteliukov.model.domain.User;
import org.dteliukov.model.domain.UserProfile;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    Optional<AuthorizedUser> authorization(UnauthorizedUser user);
    void registration(UserProfile user);
    void updateUser(UserProfile user);
    void deleteUser(String email);
    List<User> readUsers();
    List<User> readOperators();
    Optional<UserProfile> getUserProfile(String email);
    Optional<User> getUser(String email);
    void setUserStatus(String email, String status);
}
