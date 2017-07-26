package org.tsys.sbb.dao.daoImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.tsys.sbb.dao.BoardDao;
import org.tsys.sbb.model.Board;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class BoardDaoImpl implements BoardDao {

    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @PersistenceContext
    private EntityManager em;

    public Board findBoardById(int id) {
        return null;
    }

    public List<Board> findBoardsByName(String name) {
        return null;
    }

    public List<Board> findBoardsByFromOrTo(int id) {
        return null;
    }

    public List<Board> findBoardsByFromAndTo(int from_id, int to_id) {
        return null;
    }

    public void addBoard(Board board) {

    }
}
