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

    private static final Logger logger = LoggerFactory.getLogger(UserDao.class);

    @PersistenceContext
    private EntityManager em;

    public User getUserById(int id) {

        User user = em.find(User.class, id);

        logger.info("User loaded " + user);

        return user;
    }

    @SuppressWarnings("unchecked")
    public User getUserByUsername(String username) {

        List<User> list = em.createQuery("SELECT u FROM User u WHERE username=:username ")
                .setParameter("username", username)
                .getResultList();

        if (list.isEmpty()) {
            logger.info("No user found by username = " + username);
            return null;
        }

        logger.info("User loaded by username = " + username);

        return list.get(0);
    }

    @SuppressWarnings("unchecked")
    public User getUserByEmail(String email) {

        List<User> list = em.createQuery("SELECT u FROM User u WHERE email=:email ")
                .setParameter("email", email)
                .getResultList();

        if (list.isEmpty()) {
            logger.info("No user found by email = " + email);
            return null;
        }

        logger.info("User loaded by email = " + email);

        return list.get(0);
    }


    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {

        List<User> list = em.createQuery("FROM User").getResultList();

        list.forEach(user -> logger.info("Getting all user, got one " + user));

        return list;
    }

    public void addUser(User user) {

        em.persist(user);

        logger.info("User added " + user);
    }

    public void editUser(User user) {

        em.merge(user);

        logger.info("User updated " + user);
    }

    public void deleteUser(int id) {

        User user = em.find(User.class, id);

        if (user != null) {
            em.remove(user);
            logger.info("User deleted, ID was " + id);
        }
    }
}