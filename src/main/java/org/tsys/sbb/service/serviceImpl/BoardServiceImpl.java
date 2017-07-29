package org.tsys.sbb.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tsys.sbb.dao.BoardDao;
import org.tsys.sbb.model.Board;
import org.tsys.sbb.service.BoardService;

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
    public List<Board> findBoardsByName(String name) {
        return boardDao.findBoardsByName(name);
    }

    @Transactional
    public List<Board> findBoardsByFromOrTo(int id) {
        return boardDao.findBoardsByFromOrTo(id);
    }

    @Transactional
    public List<Board> findBoardsByFromAndTo(int from_id, int to_id) { return boardDao.findBoardsByFromAndTo(from_id, to_id); }

    @Transactional
    public List<Board> getAllBoards() { return boardDao.getAllBoards(); }

    @Transactional
    public void addBoard(Board board) { boardDao.addBoard(board); }
}
