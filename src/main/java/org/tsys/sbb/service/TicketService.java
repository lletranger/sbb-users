package org.tsys.sbb.service;

import org.tsys.sbb.dto.PassengerDto;
import org.tsys.sbb.dto.TicketDto;
import org.tsys.sbb.model.Board;
import org.tsys.sbb.model.Passenger;
import org.tsys.sbb.model.Ticket;
import org.tsys.sbb.model.User;

import java.util.List;

public interface TicketService {

    /**
     * Gets a {@link Ticket} from the database
     *
     * @param id integer
     * @return a single {@link Ticket} with the specified {@link Ticket#ticket_id} or null
     */
    Ticket findTicketById(int id);

    /**
     * Gets all existing {@link Ticket}s from the database
     *
     * @return an array list of {@link Ticket}s or null
     */
    List<Ticket> findAllTickets();

    /**
     * Gets all existing {@link Ticket}s from the database
     * for the specified {@link Board}
     *
     * @param board_id integer
     * @return an array list of {@link Ticket}s
     * with the specified {@link Board#board_id} or null
     */
    List<Ticket> findTicketsByBoardId(int board_id);

    /**
     * Gets all existing {@link Ticket}s from the database
     * for the specified {@link User}
     *
     * @param user_id integer
     * @return an array list of {@link Ticket}s
     * with the specified {@link User#user_id} or null
     */
    List<TicketDto> findTicketsByUserId(int user_id);

    /**
     * Saves a new {@link Ticket} to the database
     *
     * @param ticket {@link Ticket}
     */
    void addTicket(Ticket ticket);

    /**
     * Deletes given {@link Ticket} from the database
     *
     * @param id integer
     */
    void deleteTicket(int id);

    /**
     * Checks if the {@link Ticket} contains the same {@link Passenger}
     * reconstruct from the given {@link PassengerDto}
     *
     * @param ticket       {@link Ticket}
     * @param passengerDto {@link PassengerDto}
     * @return true or false
     */
    boolean isPassOnBoard(Ticket ticket, PassengerDto passengerDto);

    /**
     * Creates a new {@link Ticket} for the {@link Board} found by its  {@link Board#board_id}
     * with the {@link Passenger} reconstruct from the given {@link PassengerDto}
     * and with {@link User#user_id} set to the current  {@link User}
     *
     * @param passengerDto {@link PassengerDto}
     * @param id           {@link Board#board_id}
     * @param sessionUser  {@link User}
     * @return a new {@link Ticket}
     */
    Ticket createTicket(PassengerDto passengerDto, int id, User sessionUser);
}