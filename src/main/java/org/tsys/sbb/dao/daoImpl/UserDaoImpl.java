package org.tsys.sbb.dao.daoImpl;

import org.tsys.sbb.dao.UserDao;
import org.tsys.sbb.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDao.class);

    @PersistenceContext
    private EntityManager em;

    public User getUserById(int id) {
        User user = em.find(User.class, id);
        LOGGER.info("User loaded by its ID "
                .concat(user.toString()));
        return user;
    }

    @SuppressWarnings("unchecked")
    public User getUserByUsername(String username) {
        List<User> list = em.createQuery("SELECT u FROM User u WHERE username=:username ")
                .setParameter("username", username)
                .getResultList();

        if (list.isEmpty()) {
            LOGGER.info("No user found by username "
                    .concat(username));
            return null;
        }

        LOGGER.info("User loaded by username "
                .concat(username));
        return list.get(0);
    }

    @SuppressWarnings("unchecked")
    public User getUserByEmail(String email) {
        List<User> list = em.createQuery("SELECT u FROM User u WHERE email=:email ")
                .setParameter("email", email)
                .getResultList();

        if (list.isEmpty()) {
            LOGGER.info("No user found by email "
                    .concat(email));
            return null;
        }

        LOGGER.info("User loaded by email "
                .concat(email));
        return list.get(0);
    }


    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        List<User> list = em.createQuery("FROM User").getResultList();
        list.forEach(user -> LOGGER.info("Getting all user, got one "
                .concat(user.toString())));
        return list;
    }

    public void addUser(User user) {
        em.persist(user);
        LOGGER.info("User added "
                .concat(user.toString()));
    }

    public void editUser(User user) {
        em.merge(user);
        LOGGER.info("User updated "
                .concat(user.toString()));
    }

    public void deleteUser(int id) {
        User user = em.find(User.class, id);
        if (user != null) {
            em.remove(user);
            LOGGER.info("User deleted, ID was "
                    .concat(String.valueOf(id)));
        }
    }
}