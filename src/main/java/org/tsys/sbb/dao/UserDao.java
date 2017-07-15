package org.tsys.sbb.dao;

import org.tsys.sbb.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();
    void addUser(User user);
    void editUser(User user);
    void deleteUser(int id);
    User getUserById(int id);
}
