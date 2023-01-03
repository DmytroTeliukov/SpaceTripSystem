package org.dteliukov.model.domain;

public class UserProfile extends User {
    private String password;
    private String role;
    private String status;

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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
