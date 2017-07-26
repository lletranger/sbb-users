package org.tsys.sbb.service;

import org.tsys.sbb.model.Train;

import java.util.List;

public interface TrainService {

    Train getTrainById(int id);
    List<Train> getAllTrains();
    void addTrain(Train train);
}
