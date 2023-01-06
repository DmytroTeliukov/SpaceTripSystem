package org.dteliukov.model.domain;

import java.util.Objects;

public class User {

    protected String lastname;
    protected String firstname;
    protected String email;
    protected String phone;

    public User() {}
    public User(String lastname, String firstname, String email, String phone) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.phone = phone;
    }

    public String getLastname() {
        return lastname;
    }

    public User lastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public String getFirstname() {
        return firstname;
    }

    public User firstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User email(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public User phone(String phone) {
        this.phone = phone;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(lastname, user.lastname) &&
                Objects.equals(firstname, user.firstname) &&
                Objects.equals(email, user.email) &&
                Objects.equals(phone, user.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastname, firstname, email, phone);
    }

    @Override
    public String toString() {
        return "User{" +
                "lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public User clone() {
        return new User(lastname, firstname, email, phone);
    }
}
