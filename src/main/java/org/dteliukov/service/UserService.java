package org.dteliukov.service;

import org.dteliukov.dao.DaoRepository;
import org.dteliukov.dao.TypeDao;
import org.dteliukov.dao.UserDao;
import org.dteliukov.exception.AuthorizationException;
import org.dteliukov.exception.RegistrationException;
import org.dteliukov.model.authorization.AuthorizedUser;
import org.dteliukov.model.authorization.UnauthorizedUser;
import org.dteliukov.model.domain.User;
import org.dteliukov.model.domain.UserProfile;
import org.dteliukov.security.SecurityPassword;

import java.util.List;

public class UserService {

    private final UserDao dao;

    public UserService() {
        dao = DaoRepository.getDao(TypeDao.MySQL).getUserDao();
    }

    public AuthorizedUser authorize(String email, String password) throws AuthorizationException {
        if (dao.getUser(email).isEmpty()) {
            throw new AuthorizationException("User with email [" + email + "] doesn't exist");
        }
        var user = new UnauthorizedUser(email, SecurityPassword.generateSecurePassword(email, password));
        var authorizedUser = dao.authorization(user);

        if (authorizedUser.isEmpty()) {
            throw new AuthorizationException("Incorrect password!");
        }

        return authorizedUser.get();
    }

    public void registration(UserProfile user, String confirmedPassword) throws RegistrationException {
        if (dao.getUser(user.getEmail()).isPresent()) {
            throw new RegistrationException("User with email [" + user.getEmail() + "] exists!");
        } else if (dao.getUser(user.getPhone()).isPresent()) {
            throw new RegistrationException("User with phone [" + user.getPhone() + "] exists!");
        } else if (!user.getPassword().equals(confirmedPassword)) {
            throw new RegistrationException("Confirmed password is not correct!");
        } else {
            user.password(SecurityPassword.generateSecurePassword(user.getEmail(), confirmedPassword));
            dao.registration(user);
        }
    }

    public void updatePassword(UserProfile user, String confirmedPassword) throws Exception {
        if (!user.getPassword().equals(confirmedPassword)) {
            throw new Exception("Confirmed password is not correct!");
        } else {
            user.password(SecurityPassword.generateSecurePassword(user.getEmail(), user.getPassword()));
            dao.updateUser(user);
        }
    }

    public void updateUserProfile(UserProfile user) {
        dao.updateUser(user);
    }

    public void setStatus(String email, String status) {
        dao.setUserStatus(email, status);
    }
    public void deleteUser(String email) {
        dao.deleteUser(email);
    }

    public List<User> readUsers() {
        return dao.readUsers();
    }

    public UserProfile getUserProfileByEmail(String email) {
        var user = dao.getUserProfile(email);

        return user.orElseThrow(() -> new RuntimeException("User does not exist!"));
    }
}
