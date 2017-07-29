package org.tsys.sbb.dao.daoImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.tsys.sbb.dao.TrainDao;
import org.tsys.sbb.model.Train;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TrainDaoImpl implements TrainDao {

    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @PersistenceContext
    private EntityManager em;

    public Train getTrainById(int id) {
        Train train = (Train)em.createQuery("SELECT t FROM Train t WHERE id=:id")
                .setParameter("id", id)
                .getSingleResult();
        logger.info("Train loaded by id: " + train);
        return train;
    }

    @SuppressWarnings("unchecked")
    public List<Train> getAllTrains() {
        List<Train> list = em.createQuery("FROM Train").getResultList();
        for(Train t : list) {
            logger.info("Getting all trains : " + t);
        }
        return list;
    }

    public void addTrain(Train train) {
        em.persist(train);
        logger.info("Train added: " + train);
    }
}
