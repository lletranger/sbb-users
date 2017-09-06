package org.tsys.sbb.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ticket")
public @Data class Ticket {

    @Id
    @Column(name = "ticket_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ticket_id;

    @NotNull(message = "Ticket must have a board!")
    @OneToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @NotNull(message = "Ticket must have a passenger!")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pass_id")
    private Passenger passenger;

    @NotNull(message = "Ticket must have a buyer!")
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}