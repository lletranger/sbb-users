package org.tsys.sbb.service;

import org.tsys.sbb.model.User;

import java.util.List;

public interface UserService {

    /**
     * Gets a {@link User} from the database
     *
     * @param id integer
     * @return a single {@link User} with the specified {@link User#user_id} or null
     */
    User getUserById(int id);

    /**
     * Gets a {@link User} from the database
     *
     * @param username {@link String}
     * @return a single {@link User} with the specified {@link User#username} or null
     */
    User getUserByUsername(String username);

    /**
     * Gets a {@link User} from the database
     *
     * @param email {@link String}
     * @return a single {@link User} with the specified {@link User#email} or null
     */
    User getUserByEmail(String email);

    /**
     * Gets all existing {@link User}s from the database
     *
     * @return an array list of {@link User}s or null
     */
    List<User> getAllUsers();

    /**
     * Saves a new {@link User} to the database
     *
     * @param user {@link User}
     */
    void addUser(User user);

    /**
     * Updates given {@link User} info in the database
     *
     * @param user {@link User}
     */
    void editUser(User user);

    /**
     * Deletes given {@link User} from the database
     *
     * @param id integer
     */
    void deleteUser(int id);

    /**
     * Checks if the {@link User} in the database with the same {@link User#username}
     * has the same {@link User#password}
     *
     * @param username    String
     * @param password String
     * @return true or false
     */
    boolean checkUser(String username, String password);
}