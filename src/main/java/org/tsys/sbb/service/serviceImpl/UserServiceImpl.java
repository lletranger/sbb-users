package org.tsys.sbb.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.tsys.sbb.dao.UserDao;
import org.tsys.sbb.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tsys.sbb.service.UserService;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    public User getUserByLogin(String login) {
        return userDao.getUserByLogin(login);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public void addUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.addUser(user);
    }

    public void editUser(User user) {
        userDao.editUser(user);
    }

    public void deleteUser(int id) {
        userDao.deleteUser(id);
    }

    public boolean checkUser(String login, String password) {
        return bCryptPasswordEncoder.matches(password,
                userDao.getUserByLogin(login).getPassword());
    }
}