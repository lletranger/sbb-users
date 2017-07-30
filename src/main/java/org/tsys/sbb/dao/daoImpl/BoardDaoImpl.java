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
        Board board = em.find(Board.class, id);
        logger.info("Board loaded by id: " + board);
        return board;
    }

    @SuppressWarnings("unchecked")
    public List<Board> findBoardsByName(String name) {
        List<Board> list = em.createQuery("SELECT b FROM Board b WHERE name = :name")
                .setParameter("name", name)
                .getResultList();
        for (Board board : list) {
            logger.info("Getting all boars by name: " + board);
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    public List<Board> findBoardsByFromOrTo(int id) {
        List<Board> list = em.createQuery("SELECT b FROM Board b WHERE from_id = :id OR to_id = :id")
                .setParameter("id", id)
                .getResultList();
        for (Board board : list) {
            logger.info("Getting all board by from/to station id: " + board);
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    public List<Board> findBoardsByFromAndTo(int from_id, int to_id) {
        List<Board> list = em.createQuery("SELECT b FROM Board b WHERE from_id = :from_id AND to_id = :to_id")
                .setParameter("from_id", from_id)
                .setParameter("to_id", to_id)
                .getResultList();
        for (Board board : list) {
            logger.info("Getting all board by from + to station id: " + board);
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    public List<Board> getAllBoards() {
        List<Board> list = em.createQuery("FROM Board").getResultList();
        for (Board board : list) {
            logger.info("Getting all boards: " + board);
        }
        return list;
    }

    public void addBoard(Board board) {
        em.persist(board);
        logger.info("Board added: " + board);
    }
}