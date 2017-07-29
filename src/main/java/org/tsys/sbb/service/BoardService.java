package org.tsys.sbb.service;

import org.tsys.sbb.model.Board;

import java.util.List;

public interface BoardService {

    Board findBoardById(int id);
    List<Board> findBoardsByName(String name);
    List<Board> findBoardsByFromOrTo(int id);
    List<Board> findBoardsByFromAndTo(int from_id, int to_id);
    List<Board> getAllBoards();
    void addBoard(Board board);
}
