package org.tsys.sbb.model;

import javax.persistence.*;

@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @Column(name = "ticket_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ticket_id;

    @Column(name = "board_id")
    private int board_id;

    @Column(name = "passenger_id")
    private int passenger_id;

    @Column(name = "user_id")
    private int user_id;

    public Ticket() {
    }

    public int getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(int ticket_id) {
        this.ticket_id = ticket_id;
    }

    public int getBoard_id() {
        return board_id;
    }

    public void setBoard_id(int board_id) {
        this.board_id = board_id;
    }

    public int getPassenger_id() {
        return passenger_id;
    }

    public void setPassenger_id(int passenger_id) {
        this.passenger_id = passenger_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticket_id=" + ticket_id +
                ", board_id=" + board_id +
                ", passenger_id=" + passenger_id +
                '}';
    }
}