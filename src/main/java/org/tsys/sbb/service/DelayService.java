package org.tsys.sbb.service;

import org.tsys.sbb.model.Board;
import org.tsys.sbb.model.Delay;

import java.util.List;

public interface DelayService {

    /**
     * Gets all existing {@link Delay}s from the database
     * for the specified {@link Board} by its {@link Board#board_id}
     *
     * @param board_id integer
     * @return an array list of {@link Delay}s or null
     */
    List<Delay> getDelayByBoardId(int board_id);

    /**
     * Saves a new {@link Delay} to the database
     *
     * @param delay {@link Delay}
     */
    void addDelay(Delay delay);
}