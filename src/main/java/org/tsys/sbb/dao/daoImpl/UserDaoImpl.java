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

    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @PersistenceContext
    private EntityManager em;

    public User getUserById(int id) {
        User user = (User)em.createQuery("SELECT u FROM User u WHERE id=:id")
                .setParameter("id", id)
                .getSingleResult();
        logger.info("User successfully loaded. User details: " + user);
        return user;
    }

    public User getUserByLogin(String login) {
        User user = (User)em.createQuery("SELECT u FROM User u WHERE login=:login")
                .setParameter("login", login)
                .getSingleResult();
        logger.info("User successfully loaded. User details: " + user);
        return user;
    }

    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        List<User> userList = em.createQuery("FROM User").getResultList();
        for(User user : userList){
            logger.info("Users list: " + user);
        }
        return userList;
    }

    public void addUser(User user) {
        em.persist(user);
        logger.info("User is successfully added. User details: " + user);
    }


    public void editUser(User user) {
        em.merge(user);
        logger.info("User is successfully update. User details: " + user);
    }

    public void deleteUser(int id) {
        User user = em.find(User.class, id);

        if(user !=null) {
            em.remove(user);
        }
        logger.info("User is successfully removed. User details: " + user);
    }
}
