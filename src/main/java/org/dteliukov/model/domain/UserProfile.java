package org.dteliukov.model.domain;

import java.util.Objects;

public class UserProfile extends User {
    private String password;
    private String role;
    private String status;

    public UserProfile() {}
    public UserProfile(String lastname, String firstname, String email, String phone,
                       String password, String role, String status) {
        super(lastname, firstname, email, phone);
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public UserProfile password(String password) {
        this.password = password;
        return this;
    }

    public String getRole() {
        return role;
    }

    public UserProfile role(String role) {
        this.role = role;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public UserProfile status(String status) {
        this.status = status;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserProfile profile = (UserProfile) o;
        return Objects.equals(password, profile.password) &&
                Objects.equals(role, profile.role) &&
                Objects.equals(status, profile.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), password, role, status);
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", status='" + status + '\'' +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public UserProfile clone() {
        return new UserProfile(lastname, firstname, email, phone, password, role, status);
    }

}
