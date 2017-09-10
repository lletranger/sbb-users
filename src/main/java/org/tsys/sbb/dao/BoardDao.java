package org.tsys.sbb.dao;

import org.tsys.sbb.model.Board;

import java.util.List;

public interface BoardDao {

    Board getBoardById(int id);

    List<Board> getBoardsByFromId(int from_id);

    List<Board> getBoardsByToId(int to_id);

    List<Board> getBoardsByFromAndToIds(int from_id, int to_id);

    List<Board> getAllBoards();

    void addBoard(Board board);
}