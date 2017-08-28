package org.tsys.sbb.service;

import org.tsys.sbb.model.Train;

import java.util.List;

public interface TrainService {

    /**
     * Gets a {@link Train} from the database
     *
     * @param id integer
     * @return a single {@link Train} with the specified {@link Train#train_id} or null
     */
    Train getTrainById(int id);

    /**
     * Gets all existing {@link Train}s from the database
     *
     * @return an array list of {@link Train}s or null
     */
    List<Train> getAllTrains();
}