package org.tsys.sbb.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tsys.sbb.dto.ScheduleDto;
import org.tsys.sbb.model.Board;
import org.tsys.sbb.service.BoardService;
import org.tsys.sbb.service.DelayService;
import org.tsys.sbb.service.ScheduleService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {

    private BoardService boardService;
    private DelayService delayService;

    @Autowired
    public void setBoardService(BoardService boardService) {
        this.boardService = boardService;
    }

    @Autowired
    public void setDelayService(DelayService delayService) {
        this.delayService = delayService;
    }

    public List<ScheduleDto> getSchedule(int id) {

        List<Board> boards = boardService.findBoardsByFrom(id);
        boards.addAll(boardService.findBoardsByTo(id));

        List<ScheduleDto> list = new ArrayList<>();

        for(Board b : boards) {
            list.add(null);
        }
        return list;
    }
}
