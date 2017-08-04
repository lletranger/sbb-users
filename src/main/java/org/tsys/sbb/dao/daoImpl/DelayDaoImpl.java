package org.tsys.sbb.dao.daoImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.tsys.sbb.dao.DelayDao;
import org.tsys.sbb.model.Delay;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class DelayDaoImpl implements DelayDao {

    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    public List<Delay> getDelayByBoardId(int board_id) {
        List<Delay> list = em.createQuery("SELECT d FROM Delay d WHERE board_id=:board_id")
                .setParameter("board_id", board_id)
                .getResultList();

        for (Delay d : list) {
            logger.info("Getting all delays by board id: " + d);
        }

        return list;
    }

    public void addDelay(Delay delay) {
        em.persist(delay);
        logger.info("Delay added: " + delay);
    }
}