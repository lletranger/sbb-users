package org.tsys.sbb.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.tsys.sbb.dao.UserDao;
import org.tsys.sbb.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tsys.sbb.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Transactional
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Transactional
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Transactional
    public void editUser(User user) {
        userDao.editUser(user);
    }

    @Transactional
    public void deleteUser(int id) {
       userDao.deleteUser(id);
    }
}
