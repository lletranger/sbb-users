package org.tsys.sbb.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tsys.sbb.dao.DelayDao;
import org.tsys.sbb.model.Delay;
import org.tsys.sbb.service.DelayService;
import org.tsys.sbb.util.DistanceAndTimeUtil;

import java.util.List;

@Service
@Transactional
public class DelayServiceImpl implements DelayService {

    private DelayDao delayDao;

    @Autowired
    public void setDelayDao(DelayDao delayDao) {
        this.delayDao = delayDao;
    }

    public List<Delay> getDelayByBoardId(int board_id) {
        return delayDao.getDelayByBoardId(board_id);
    }

    public void addDelay(Delay delay) {
        if(!DistanceAndTimeUtil.getStringDelay(delay.getDelay_time()).equals("0m")) {
        delayDao.addDelay(delay);
        }
    }
}