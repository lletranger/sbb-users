package org.tsys.sbb.dao;

import org.tsys.sbb.model.Train;

import java.util.List;

public interface TrainDao {
    Train getTrainById(int id);
    List<Train> getAllTrains();
    void addTrain(Train train);
}
