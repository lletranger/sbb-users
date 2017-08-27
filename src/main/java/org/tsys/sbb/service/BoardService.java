package org.tsys.sbb.service;

import org.tsys.sbb.dto.BoardDto;
import org.tsys.sbb.dto.PassengerDto;
import org.tsys.sbb.model.Board;

import java.util.Date;
import java.util.List;

public interface BoardService {

    /**
     * Looking for particular Board
     * @param id
     * @return
     */
    Board findBoardById(int id);

    List<Board> findBoardsByFrom(int id);

    List<Board> findBoardsByTo(int id);

    List<Board> findBoardsByFromAndTo(int from_id, int to_id);

    List<Board> getAllBoards();

    void addBoard(Board board);

    List<Board> findBoards(int id1, int id2);

    Date findExpectedArrival(Board board);

    Date findArrival(int board_id);

    boolean isAvailable(int id);

    boolean passExists(int id, PassengerDto passengerDto);

    BoardDto getDtoFromBoard(Board board);

    BoardDto getFullDto(Board b);

    BoardDto getSearchDto(Board board);

    String getFromStatus(Board board);

    String getToStatus(Board board);

}