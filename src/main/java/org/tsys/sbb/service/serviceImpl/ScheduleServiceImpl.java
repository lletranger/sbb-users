package org.tsys.sbb.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tsys.sbb.dto.BoardDto;
import org.tsys.sbb.dto.ScheduleDto;
import org.tsys.sbb.service.BoardService;
import org.tsys.sbb.service.ScheduleService;
import org.tsys.sbb.service.StationService;
import org.tsys.sbb.util.DistanceAndTimeUtil;

import java.util.ArrayList;
import java.util.Comparator;
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
            BoardDto boardDto= new BoardDto();
            boardDto.setName(board.getName());
            boardDto.setDeparture(DistanceAndTimeUtil.getStringDate(board.getDeparture()));
            boardDto.setTo(stationService.getStationById(board.getTo_id()).getName());
            boardDto.setStatus(boardService.getFromStatus(board));
            from.add(boardDto);
        });

        boardService.findBoardsByTo(id).forEach(board -> {
            BoardDto bDto = new BoardDto();
            bDto.setName(board.getName());
            bDto.setExpectedArrival(DistanceAndTimeUtil.getStringDate(boardService.findExpectedArrival(board)));
            bDto.setFrom(stationService.getStationById(board.getFrom_id()).getName());
            bDto.setArrival(DistanceAndTimeUtil.getStringDate(boardService.findArrival(board.getBoard_id())));
            bDto.setStatus(boardService.getToStatus(board));
            to.add(bDto);
        });

        to.sort(Comparator.comparing(BoardDto::getExpectedArrival));

        dto.setName(stationService.getStationById(id).getName());
        dto.setFrom(from);
        dto.setTo(to);

        return dto;
    }
}