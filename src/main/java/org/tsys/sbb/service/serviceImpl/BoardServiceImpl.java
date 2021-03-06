package org.tsys.sbb.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tsys.sbb.dao.BoardDao;
import org.tsys.sbb.dto.BoardDto;
import org.tsys.sbb.dto.PassengerDto;
import org.tsys.sbb.model.*;
import org.tsys.sbb.service.*;
import org.tsys.sbb.util.DistanceAndTimeUtil;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {

    private BoardDao boardDao;
    private DelayService delayService;
    private StationService stationService;
    private TicketService ticketService;
    private TrainService trainService;

    @Autowired
    public void setBoardDao(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    @Autowired
    public void setDelayService(DelayService delayService) {
        this.delayService = delayService;
    }

    @Autowired
    public void setStationService(StationService stationService) {
        this.stationService = stationService;
    }

    @Autowired
    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Autowired
    public void setTrainService(TrainService trainService) {
        this.trainService = trainService;
    }

    public Board getBoardById(int id) {

        Board board;

        try {
            board = boardDao.getBoardById(id);
        } catch (Exception e) {
            board = null;
        }

        return board;
    }

    public List<Board> getBoardsByFrom(int id) {
        return boardDao.getBoardsByFromId(id);
    }

    public List<Board> getBoardsByTo(int id) {
        return boardDao.getBoardsByToId(id);
    }

    public List<Board> getBoardsByFromAndTo(int from_id, int to_id) {
        return boardDao.getBoardsByFromAndToIds(from_id, to_id);
    }

    public List<Board> getAllBoards() {
        return boardDao.getAllBoards();
    }

    public void addBoard(Board board) {
        boardDao.addBoard(board);
    }

    public List<Board> findBoards(int id1, int id2, String time1, String time2) {

        List<Board> resultList;
        List<Board> finalList;

        if (id2 == 0) {
            resultList = getBoardsByFrom(id1);
        } else if (id1 == 0) {
            resultList = getBoardsByTo(id2);
        } else {
            resultList = getBoardsByFromAndTo(id1, id2);
        }

        if (time1.equals("") && time2.equals("")) {
            finalList = resultList;
        } else if (!time1.equals("") && time2.equals("")) {
            finalList = resultList.stream()
                    .filter(board -> DistanceAndTimeUtil.getStringDate(board.getDeparture()).compareTo(time1) >= 0)
                    .collect(Collectors.toList());
        } else if (time1.equals("")) {
            finalList = resultList.stream()
                    .filter(board -> DistanceAndTimeUtil.getStringDate(board.getDeparture()).compareTo(time2) <= 0)
                    .collect(Collectors.toList());
        } else {
            finalList = resultList.stream()
                    .filter(board -> DistanceAndTimeUtil.getStringDate(board.getDeparture()).compareTo(time1) >= 0)
                    .filter(board -> DistanceAndTimeUtil.getStringDate(board.getDeparture()).compareTo(time2) <= 0)
                    .collect(Collectors.toList());
        }

        return finalList;
    }

    public Date findExpectedArrival(Board board) {

        Station from = stationService.getStationById(board.getFrom_id());
        Station to = stationService.getStationById(board.getTo_id());
        Train train = trainService.getTrainById(board.getTrain_id());
        int distance = (int) DistanceAndTimeUtil.getDistance(from, to);

        return new Date(board.getDeparture().getTime()
                + DistanceAndTimeUtil.getTime(DistanceAndTimeUtil.getJourneyTime(distance, train)));
    }

    private Date findDelays(Board board) {

        List<Delay> delays = delayService.getDelayByBoardId(board.getBoard_id());
        Delay d = DistanceAndTimeUtil.getResultingDelay(delays);

        return d.getDelay_time();
    }

    public Date findArrival(int board_id) {

        Board board = getBoardById(board_id);

        Station from = stationService.getStationById(board.getFrom_id());
        Station to = stationService.getStationById(board.getTo_id());
        Train train = trainService.getTrainById(board.getTrain_id());

        int distance = (int) DistanceAndTimeUtil.getDistance(from, to);
        Date arrival = new Date(board.getDeparture().getTime() + DistanceAndTimeUtil.getTime(DistanceAndTimeUtil.getJourneyTime(distance, train)));

        List<Delay> delays = delayService.getDelayByBoardId(board.getBoard_id());

        if (!delays.isEmpty()) {
            Delay d = DistanceAndTimeUtil.getResultingDelay(delays);
            String delay = DistanceAndTimeUtil.getStringDelay(d.getDelay_time());
            arrival = new Date(board.getDeparture().getTime()
                    + DistanceAndTimeUtil.getTime(DistanceAndTimeUtil.getJourneyTime(distance, train))
                    + DistanceAndTimeUtil.getTime(delay));
        }

        return arrival;
    }

    public boolean isAvailable(int id) {

        return ticketService.getTicketsByBoardId(id).size() <
                trainService.getTrainById(boardDao.getBoardById(id).getTrain_id()).getSeats();
    }

    public boolean passExists(int id, PassengerDto passengerDto) {

        return ticketService.getTicketsByBoardId(id)
                .stream()
                .anyMatch(ticket -> ticketService.isPassOnBoard(ticket, passengerDto));
    }

    public BoardDto getDtoFromBoard(Board board) {

        BoardDto boardDto = new BoardDto();
        Station fromStation = stationService.getStationById(board.getFrom_id());
        Station toStation = stationService.getStationById(board.getTo_id());
        Train train = trainService.getTrainById(board.getTrain_id());

        boardDto.setName(board.getName());
        boardDto.setFrom(fromStation.getName());
        boardDto.setTo(toStation.getName());
        boardDto.setDeparture(DistanceAndTimeUtil.getStringDate(board.getDeparture()));

        int distance = (int) DistanceAndTimeUtil.getDistance(fromStation, toStation);
        boardDto.setDistance(distance);

        boardDto.setJourneyTime(DistanceAndTimeUtil.getJourneyTime(distance, train));
        boardDto.setAverageSpeed(train.getSpeed_percents() * 45 / 100);

        return boardDto;
    }

    public BoardDto getFullDto(Board board) {

        BoardDto boardDto = new BoardDto();
        Station fromStation = stationService.getStationById(board.getFrom_id());
        Station toStation = stationService.getStationById(board.getTo_id());
        Train train = trainService.getTrainById(board.getTrain_id());

        boardDto.setId(board.getBoard_id());
        boardDto.setName(board.getName());
        boardDto.setFrom(fromStation.getName());
        boardDto.setTo(toStation.getName());
        boardDto.setDeparture(DistanceAndTimeUtil.getStringDate(board.getDeparture()));

        int distance = (int) DistanceAndTimeUtil.getDistance(fromStation, toStation);
        boardDto.setDistance(distance);

        boardDto.setJourneyTime(DistanceAndTimeUtil.getJourneyTime(distance, train));

        Date arrival = new Date(board.getDeparture().getTime() + DistanceAndTimeUtil.getTime(DistanceAndTimeUtil.getJourneyTime(distance, train)));
        boardDto.setExpectedArrival(DistanceAndTimeUtil.getStringDate(arrival));
        if (!delayService.getDelayByBoardId(board.getBoard_id()).isEmpty()) {
            boardDto.setDelay(DistanceAndTimeUtil.getStringDelay(DistanceAndTimeUtil.getResultingDelay(
                    delayService.getDelayByBoardId(board.getBoard_id())).getDelay_time()));
        }
        arrival = findArrival(board.getBoard_id());
        boardDto.setArrival(DistanceAndTimeUtil.getStringDate(arrival));
        boardDto.setCanAddDelay(!DistanceAndTimeUtil.isAlreadyArrived(board.getDeparture(), arrival)
                && DistanceAndTimeUtil.isDepartedOrArrived(board.getDeparture()) ? "true" : "false");

        return boardDto;
    }


    public BoardDto getSearchDto(Board board) {

        BoardDto boardDto = new BoardDto();
        Station fromStation = stationService.getStationById(board.getFrom_id());
        Station toStation = stationService.getStationById(board.getTo_id());
        Train train = trainService.getTrainById(board.getTrain_id());

        boardDto.setId(board.getBoard_id());
        boardDto.setName(board.getName());
        boardDto.setFrom(fromStation.getName());
        boardDto.setTo(toStation.getName());

        List<Ticket> tickets = ticketService.getTicketsByBoardId(board.getBoard_id());
        String departure = DistanceAndTimeUtil.getStringDate(board.getDeparture());

        boardDto.setTicketsAvailable((tickets.size() < train.getSeats()) && (!DistanceAndTimeUtil.isTenMinsGap(departure)));
        boardDto.setDeparture(departure);
        boardDto.setAverageSpeed(train.getSpeed_percents() * 45 / 100);

        int distance = (int) DistanceAndTimeUtil.getDistance(fromStation, toStation);
        boardDto.setDistance(distance);

        String journeyTime = DistanceAndTimeUtil.getJourneyTime(distance, train);
        boardDto.setJourneyTime(journeyTime);

        Date expectedArrival = new Date(board.getDeparture().getTime() + DistanceAndTimeUtil.getTime(journeyTime));
        boardDto.setExpectedArrival(DistanceAndTimeUtil.getStringDate(expectedArrival));

        List<Delay> delays = delayService.getDelayByBoardId(board.getBoard_id());
        if (!delays.isEmpty()) {
            Delay d = DistanceAndTimeUtil.getResultingDelay(delays);
            String delay = DistanceAndTimeUtil.getStringDelay(d.getDelay_time());
            boardDto.setDelay(delay);
            expectedArrival = new Date(board.getDeparture().getTime()
                    + DistanceAndTimeUtil.getTime(DistanceAndTimeUtil.getJourneyTime(distance, train))
                    + DistanceAndTimeUtil.getTime(delay));
        }

        boardDto.setArrival(DistanceAndTimeUtil.getStringDate(expectedArrival));
        return boardDto;
    }

    public String getFromStatus(Board board) {

        if (DistanceAndTimeUtil.isDepartedOrArrived(board.getDeparture())) {
            return "Departed at "
                    .concat(DistanceAndTimeUtil.getStringDate(board.getDeparture()));
        }

        if (DistanceAndTimeUtil.isDepartingOrArriving(board.getDeparture())) {
            return "Departing";
        }

        return " ";
    }

    public String getToStatus(Board board) {

        Date arriving = findArrival(board.getBoard_id());

        if (DistanceAndTimeUtil.isDepartedOrArrived(arriving)) {
            return "Arrived at "
                    .concat(DistanceAndTimeUtil.getStringDate(arriving));
        }

        if (DistanceAndTimeUtil.isDepartingOrArriving(arriving)) {
            return "Approaching";
        }

        if (!DistanceAndTimeUtil.getStringDelay(findDelays(board)).equals("0m")) {
            return "Delayed by "
                    .concat(DistanceAndTimeUtil.getStringDelay(findDelays(board)));
        }

        if (DistanceAndTimeUtil.isDepartedOrArrived(board.getDeparture())) {
            return "On route";
        }

        return " ";
    }
}
