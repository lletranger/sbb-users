package org.tsys.sbb.service;

import org.tsys.sbb.model.User;

import java.util.List;

public interface UserService {

    User getUserById(int id);

    User getUserByLogin(String login);

    List<User> getAllUsers();

    void addUser(User user);

    void editUser(User user);

    void deleteUser(int id);
}