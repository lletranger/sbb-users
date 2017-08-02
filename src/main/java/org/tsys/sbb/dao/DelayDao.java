package org.tsys.sbb.dao;

import org.tsys.sbb.model.Delay;

public interface DelayDao {

    Delay getDelayByBoardId(int board_id);

    void addDelay(Delay delay);
}