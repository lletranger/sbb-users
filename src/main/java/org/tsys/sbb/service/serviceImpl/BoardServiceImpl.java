package org.tsys.sbb.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tsys.sbb.dao.BoardDao;
import org.tsys.sbb.model.Board;
import org.tsys.sbb.model.Delay;
import org.tsys.sbb.model.Station;
import org.tsys.sbb.model.Train;
import org.tsys.sbb.service.BoardService;
import org.tsys.sbb.service.DelayService;
import org.tsys.sbb.service.StationService;
import org.tsys.sbb.service.TrainService;
import org.tsys.sbb.util.DistanceAndTimeUtil;

import java.util.Date;
import java.util.List;


@Service
@Transactional
public class BoardServiceImpl implements BoardService {

    private BoardDao boardDao;
    private StationService stationService;
    private DelayService delayService;
    private TrainService trainService;

    @Autowired
    public void setBoardDao(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    @Autowired
    public void setStationService(StationService stationService) {
        this.stationService = stationService;
    }

    @Autowired
    public void setDelayService(DelayService delayService) {
        this.delayService = delayService;
    }

    @Autowired
    public void setTrainService(TrainService trainService) {
        this.trainService = trainService;
    }

    public Board findBoardById(int id) {
        return boardDao.findBoardById(id);
    }

    public List<Board> findBoardsByFrom(int id) {
        return boardDao.findBoardsByFrom(id);
    }

    public List<Board> findBoardsByTo(int id) {
        return boardDao.findBoardsByTo(id);
    }

    public List<Board> findBoardsByFromAndTo(int from_id, int to_id) {
        return boardDao.findBoardsByFromAndTo(from_id, to_id);
    }

    public List<Board> getAllBoards() {
        return boardDao.getAllBoards();
    }

    public void addBoard(Board board) {
        boardDao.addBoard(board);
    }

    public List<Board> findBoards(int id1, int id2) {

        List<Board> resultList;

        if (id2 == 0) {
            resultList = findBoardsByFrom(id1);
        } else if (id1 == 0) {
            resultList = findBoardsByTo(id2);
        } else {
            resultList = findBoardsByFromAndTo(id1, id2);
        }

        return resultList;
    }

    public Date findArrival(int board_id) {

        Board board = findBoardById(board_id);

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
}