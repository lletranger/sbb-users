package org.tsys.sbb.service.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.tsys.sbb.dao.UserDao;
import org.tsys.sbb.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tsys.sbb.service.TicketService;
import org.tsys.sbb.service.UserService;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private TicketService ticketService;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Autowired
    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public User getUserById(int id) {

        User user = null;

        try { user = userDao.getUserById(id);
        } catch (Exception e) { }

        return user;
    }

    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
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
        ticketService.getTicketsByUserId(id).forEach(ticket -> ticketService.deleteTicket(ticket.getId()));
        userDao.deleteUser(id);
    }

    public boolean checkUser(String username, String password) {
        return bCryptPasswordEncoder.matches(password, getUserByUsername(username).getPassword());
    }
}