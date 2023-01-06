package org.dteliukov.dao.mysql;

import org.dteliukov.dao.UserDao;
import org.dteliukov.dao.mysql.queries.UserSqlQuery;
import org.dteliukov.model.authorization.AuthorizedUser;
import org.dteliukov.model.authorization.UnauthorizedUser;
import org.dteliukov.model.domain.User;
import org.dteliukov.model.domain.UserProfile;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserMySqlDao implements UserDao {

    @Override
    public Optional<AuthorizedUser> authorization(UnauthorizedUser user) {
        int n = 1;
        try(var connection = MySqlConnection.getConnection()) {
            try (var statement = connection.prepareStatement(UserSqlQuery.AUTHORIZATION.getQuery())) {
                statement.setString(n++, user.email());
                statement.setString(n, user.password());
                try (var resultSet = statement.executeQuery()){
                    if (resultSet.next()) {
                        return Optional.of(new AuthorizedUser(user.email(),
                                resultSet.getString("role_name")));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public void registration(UserProfile user) {
        int n = 1;
        try(var connection = MySqlConnection.getConnection()) {
            try (var statement = connection.prepareCall(UserSqlQuery.REGISTRATION.getQuery())) {
                statement.setString(n++, user.getLastname());
                statement.setString(n++, user.getFirstname());
                statement.setString(n++, user.getEmail());
                statement.setString(n++, user.getPhone());
                statement.setString(n++, user.getPassword());
                statement.setString(n, user.getRole());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateUser(UserProfile user) {
        int n = 1;
        try (var connection = MySqlConnection.getConnection()){
            try (var statement = connection.prepareStatement(UserSqlQuery.UPDATE_USER.getQuery())) {
                statement.setString(n++, user.getLastname());
                statement.setString(n++, user.getFirstname());
                statement.setString(n++, user.getEmail());
                statement.setString(n++, user.getPhone());
                statement.setString(n, user.getPassword());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteUser(String email) {
        try (var connection = MySqlConnection.getConnection()){
            try (var statement = connection.prepareStatement(UserSqlQuery.DELETE_USER.getQuery())) {
                statement.setString(1, email);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> readUsers() {
        List<User> users = new ArrayList<>();
        try(var connection = MySqlConnection.getConnection()) {
            try(var statement = connection.prepareStatement(UserSqlQuery.READ_USERS.getQuery())) {
                try (var resultSet = statement.executeQuery()) {
                    User user = new User();
                    while (resultSet.next()) {
                        user.lastname(resultSet.getString("lastname"))
                                .firstname(resultSet.getString("firstname"))
                                .email(resultSet.getString("email"))
                                .phone(resultSet.getString("phone"));
                        users.add(user.clone());
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    @Override
    public Optional<UserProfile> getUserProfile(String email) {
        try(var connection = MySqlConnection.getConnection()) {
            try(var statement = connection.prepareStatement(UserSqlQuery.GET_USER.getQuery())) {
                statement.setString(1, email);
                try (var resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        UserProfile profile = new UserProfile(resultSet.getString("lastname"),
                                resultSet.getString("firstname"),
                                resultSet.getString("email"),
                                resultSet.getString("phone"),
                                resultSet.getString("password"),
                                resultSet.getString("role_name"),
                                resultSet.getString("user_status_name"));
                        return Optional.of(profile);
                    }
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> getUser(String email) {
        try(var connection = MySqlConnection.getConnection()) {
            try(var statement = connection.prepareStatement(UserSqlQuery.GET_USER.getQuery())) {
                statement.setString(1, email);
                try (var resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        User user = new User(resultSet.getString("lastname"),
                                resultSet.getString("firstname"),
                                resultSet.getString("email"),
                                resultSet.getString("phone"));
                        return Optional.of(user);
                    }
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setUserStatus(String email, String status) {
        int n = 1;
        try (var connection = MySqlConnection.getConnection()){
            try (var statement = connection.prepareCall(UserSqlQuery.UPDATE_STATUS.getQuery())) {
                statement.setString(n++, email);
                statement.setString(n, status);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
