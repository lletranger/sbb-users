package org.tsys.sbb.service;

import org.tsys.sbb.dto.BoardDto;
import org.tsys.sbb.dto.PassengerDto;
import org.tsys.sbb.model.*;

import java.util.Date;
import java.util.List;

public interface BoardService {

    /**
     * Gets a {@link Board} from the database
     *
     * @param id integer
     * @return a single {@link Board} with the specified {@link Board#board_id} or null
     */
    Board findBoardById(int id);

    /**
     * Gets all existing {@link Board}s from the database
     * from some {@link Station} by its {@link Board#from_id}
     *
     * @param id integer
     * @return an array list of {@link Board}s or null
     */
    List<Board> findBoardsByFrom(int id);

    /**
     * Gets all existing {@link Board}s from the database
     * to some {@link Station} by its {@link Board#to_id}
     *
     * @param id integer
     * @return an array list of {@link Board}s or null
     */
    List<Board> findBoardsByTo(int id);

    /**
     * Gets all existing {@link Board}s from the database
     * from some {@link Station} to some {@link Station}
     * by its {@link Board#from_id} and {@link Board#to_id}
     *
     * @param from_id integer
     * @param to_id   integer
     * @return an array list of {@link Board}s or null
     */
    List<Board> findBoardsByFromAndTo(int from_id, int to_id);

    /**
     * Gets all existing {@link Board}s from the database
     *
     * @return an array list of {@link Board}s or null
     */
    List<Board> getAllBoards();

    /**
     * Saves a new {@link Board} to the database
     *
     * @param board {@link Board}
     */
    void addBoard(Board board);

    /**
     * Gets all existing {@link Board}s from the database from
     * some {@link Station} or to some {@link Station} by its
     * {@link Board#from_id} or {@link Board#to_id} by passing it
     * to {@link #findBoardsByFrom(int)} and {@link #findBoardsByTo(int)} methods
     *
     * @param id1 integer
     * @param id2 integer
     * @return an array list of {@link Board}s or null
     */
    List<Board> findBoards(int id1, int id2);

    /**
     * Calculates an expected arrival time for the given {@link Board}
     * by adding {@link Board#departure} distance between origin {@link Station}
     * and endpoint {@link Station} divided by its {@link Train} speed
     *
     * @param board {@link Board}
     * @return an expected arrival time
     */
    Date findExpectedArrival(Board board);

    /**
     * Calculates a total arrival time for the given {@link Board} found by
     * its {@link Board#board_id} by adding to {@link Board#departure}
     * distance between origin {@link Station} and endpoint {@link Station} divided
     * by its {@link Train} speed and summing it with {@link Delay}s
     * found by {@link DelayService#getDelayByBoardId(int)} method
     *
     * @param board_id integer
     * @return a total arrival time
     */
    Date findArrival(int board_id);


    /**
     * Checks if the amount of {@link Train#seats} in the given {@link Board} found by
     * its {@link Board#board_id} is bigger than all bought {@link Ticket}s
     * for this {@link Board}
     *
     * @param id integer
     * @return true or false
     */
    boolean isAvailable(int id);

    /**
     * Checks if {@link Board} found by its {@link Board#board_id}
     * already contains {@link Passenger} reconstruct from the given {@link PassengerDto}
     *
     * @param id integer
     * @return true or false
     */
    boolean passExists(int id, PassengerDto passengerDto);

    /**
     * Creates {@link BoardDto} from the given {@link Board}
     *
     * @param board {@link Board}
     * @return a single {@link BoardDto}
     */
    BoardDto getDtoFromBoard(Board board);

    /**
     * Creates {@link BoardDto} from the given {@link Board}
     *
     * @param b {@link Board}
     * @return a single {@link BoardDto}
     */
    BoardDto getFullDto(Board b);

    /**
     * Creates {@link BoardDto} from the given {@link Board}
     *
     * @param board {@link Board}
     * @return a single {@link BoardDto}
     */
    BoardDto getSearchDto(Board board);

    /**
     * Calculates {@link Board} status by comparing its {@link Board#departure},
     * expected arrival and arrival with the current time
     *
     * @param board {@link Board}
     * @return status of the given board
     */
    String getFromStatus(Board board);

    /**
     * Calculates {@link Board} status by comparing its {@link Board#departure},
     * expected arrival and arrival with the current time
     *
     * @param board {@link Board}
     * @return status of the given board
     */
    String getToStatus(Board board);
}