package org.tsys.sbb.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tsys.sbb.dto.BoardDto;
import org.tsys.sbb.dto.ScheduleDto;
import org.tsys.sbb.service.BoardService;
import org.tsys.sbb.service.ScheduleService;
import org.tsys.sbb.service.StationService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {

    private BoardService boardService;
    private StationService stationService;

    @Autowired
    public void setBoardService(BoardService boardService) {
        this.boardService = boardService;
    }

    @Autowired
    public void setStationService(StationService stationService) {
        this.stationService = stationService;
    }

    public ScheduleDto getSchedule(int id) {

        ScheduleDto dto = new ScheduleDto();
        List<BoardDto> from = new ArrayList<>();
        List<BoardDto> to = new ArrayList<>();

        boardService.findBoardsByFrom(id).forEach(board -> {
            BoardDto bDto = new BoardDto();
            bDto.setName(board.getName());
            bDto.setFrom(stationService.getStationById(board.getFrom_id()).getName());
            from.add(bDto);
        });

        boardService.findBoardsByTo(id).forEach(board -> {
            BoardDto bDto = new BoardDto();
            bDto.setName(board.getName());
            bDto.setTo(stationService.getStationById(board.getTo_id()).getName());
            to.add(bDto);
        });

        dto.setName(stationService.getStationById(id).getName());
        dto.setFrom(from);
        dto.setTo(to);

        return dto;
    }
}
