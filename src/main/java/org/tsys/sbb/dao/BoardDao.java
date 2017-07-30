package org.tsys.sbb.dao;

import org.tsys.sbb.model.Board;

import java.util.List;

public interface BoardDao {
    Board findBoardById(int id);

    List<Board> findBoardsByName(String name);

    List<Board> findBoardsByFrom(int id);

    List<Board> findBoardsByTo(int id);

    List<Board> findBoardsByFromAndTo(int from_id, int to_id);

    List<Board> getAllBoards();

    void addBoard(Board board);
}