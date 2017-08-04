package org.tsys.sbb.dao;

import org.tsys.sbb.model.Delay;

import java.util.List;

public interface DelayDao {

    List<Delay> getDelayByBoardId(int board_id);

    void addDelay(Delay delay);
}