package org.tsys.sbb.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tsys.sbb.dao.BoardDao;
import org.tsys.sbb.model.Board;
import org.tsys.sbb.service.BoardService;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    private BoardDao boardDao;

    @Autowired
    public void setBoardDao(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    @Transactional
    public Board findBoardById(int id) {
        return boardDao.findBoardById(id);
    }

    @Transactional
    public List<Board> findBoardsByFrom(int id) {
        return boardDao.findBoardsByFrom(id);
    }

    @Transactional
    public List<Board> findBoardsByTo(int id) {
        return boardDao.findBoardsByTo(id);
    }

    @Transactional
    public List<Board> findBoardsByFromAndTo(int from_id, int to_id) {
        return boardDao.findBoardsByFromAndTo(from_id, to_id);
    }

    @Transactional
    public List<Board> getAllBoards() {
        return boardDao.getAllBoards();
    }

    @Transactional
    public void addBoard(Board board) {
        boardDao.addBoard(board);
    }

    @Transactional
    public List<Board> find(int id1, int id2) {

        List<Board> resultList = new ArrayList<>();

        if (id2 == 0) {
            resultList = findBoardsByFrom(id1);
        } else if (id1 == 0) {
            resultList = findBoardsByTo(id2);
        } else {
            resultList = findBoardsByFromAndTo(id1, id2);
        }

        return resultList;
    }
}