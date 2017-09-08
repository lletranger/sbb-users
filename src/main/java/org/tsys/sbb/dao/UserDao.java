package org.tsys.sbb.dao;

import org.tsys.sbb.model.User;

import java.util.List;

public interface UserDao {

    User getUserById(int id);

    User getUserByUsername(String username);

    User getUserByEmail(String email);

    List<User> getAllUsers();

    void addUser(User user);

    void editUser(User user);

    void deleteUser(int id);
}