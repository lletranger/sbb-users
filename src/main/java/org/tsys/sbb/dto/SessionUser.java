package org.tsys.sbb.dto;

import org.tsys.sbb.model.User;

import java.io.Serializable;

public class SessionUser implements Serializable {

    private int user_id;
    private String login;
    private String password;
    private String role;

    public SessionUser() {
        this.role = "anonym";
    }

    public SessionUser(User user) {
        this.user_id = user.getUser_id();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.role = user.getRole();
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
}