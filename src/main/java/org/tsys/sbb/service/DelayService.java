package org.tsys.sbb.service;

import org.tsys.sbb.model.Delay;

import java.util.List;

public interface DelayService {

    List<Delay> getDelaysByBoardId(int board_id);
    void addDelay(Delay delay);
}

