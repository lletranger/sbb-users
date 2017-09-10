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

    private static final Logger LOGGER = LoggerFactory.getLogger(TrainDao.class);

    @PersistenceContext
    private EntityManager em;

    public Train getTrainById(int id) {
        Train train = em.find(Train.class, id);
        LOGGER.info("Train loaded by its ID "
                .concat(train.toString()));
        return train;
    }

    @SuppressWarnings("unchecked")
    public List<Train> getAllTrains() {
        List<Train> list = em.createQuery("FROM Train").getResultList();
        list.forEach(train -> LOGGER.info("Getting all trains, got one "
                .concat(train.toString())));
        return list;
    }
}