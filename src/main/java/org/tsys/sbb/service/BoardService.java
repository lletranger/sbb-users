package org.tsys.sbb.service;

import org.tsys.sbb.model.Board;

import java.util.Date;
import java.util.List;

public interface BoardService {

    Board findBoardById(int id);

    List<Board> findBoardsByFrom(int id);

    List<Board> findBoardsByTo(int id);

    List<Board> findBoardsByFromAndTo(int from_id, int to_id);

    List<Board> getAllBoards();

    void addBoard(Board board);

    List<Board> findBoards(int id1, int id2);

    Date findArrival(int board_id);
}