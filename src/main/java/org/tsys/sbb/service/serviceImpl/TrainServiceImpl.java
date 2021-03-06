package org.tsys.sbb.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tsys.sbb.dao.TrainDao;
import org.tsys.sbb.model.Train;
import org.tsys.sbb.service.TrainService;

import java.util.List;

@Service
@Transactional
public class TrainServiceImpl implements TrainService {

    private TrainDao trainDao;

    @Autowired
    public void setTrainDao(TrainDao trainDao) {
        this.trainDao = trainDao;
    }

    public Train getTrainById(int id) {
        return trainDao.getTrainById(id);
    }

    public List<Train> getAllTrains() {
        return trainDao.getAllTrains();
    }
}