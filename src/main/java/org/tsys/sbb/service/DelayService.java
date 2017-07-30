package org.tsys.sbb.service;

import org.tsys.sbb.model.Delay;

public interface DelayService {

    Delay getDelayByBoardId(int board_id);

    void addDelay(Delay delay);
}