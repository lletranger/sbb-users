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

    private static final Logger logger = LoggerFactory.getLogger(BoardDao.class);

    @PersistenceContext
    private EntityManager em;

    public Board findBoardById(int id) {

        Board board = em.find(Board.class, id);

        logger.info("Board loaded " + board);

        return board;
    }

    @SuppressWarnings("unchecked")
    public List<Board> findBoardsByFrom(int id) {

        List<Board> list = em.createQuery("SELECT b FROM Board b WHERE from_id = :id ORDER BY departure")
                .setParameter("id", id)
                .getResultList();

        list.forEach(board -> logger.info("Getting all boards by from station, got one " + board));


        return list;
    }

    @SuppressWarnings("unchecked")
    public List<Board> findBoardsByTo(int id) {

        List<Board> list = em.createQuery("SELECT b FROM Board b WHERE to_id = :id ORDER BY departure")
                .setParameter("id", id)
                .getResultList();

        list.forEach(board -> logger.info("Getting all boards by to station, got one " + board));

        return list;
    }

    @SuppressWarnings("unchecked")
    public List<Board> findBoardsByFromAndTo(int from_id, int to_id) {

        List<Board> list = em.createQuery("SELECT b FROM Board b WHERE from_id = :from_id AND to_id = :to_id ORDER BY departure")
                .setParameter("from_id", from_id)
                .setParameter("to_id", to_id)
                .getResultList();

        list.forEach(board -> logger.info("Getting all boards by from and to stations, got one " + board));

        return list;
    }

    @SuppressWarnings("unchecked")
    public List<Board> getAllBoards() {

        List<Board> list = em.createQuery("FROM Board ORDER BY departure").getResultList();

        list.forEach(board -> logger.info("Getting all boards, got one " + board));

        return list;
    }

    public void addBoard(Board board) {

        em.persist(board);

        logger.info("Board added " + board);
    }
}