package org.tsys.sbb.model;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ticket")
public @Data class Ticket {

    @Id
    @Column(name = "ticket_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ticket_id;

    @NotNull(message = "Board id can't be null!")
    @Range(min = 1, message = "Board id can't be negative")
    @Column(name = "board_id")
    private int board_id;

    @NotNull(message = "Ticket must have a passenger!")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pass_id")
    private Passenger passenger;

    @NotNull(message = "User id can't be null!")
    @Range(min = 1, message = "User id can't be negative")
    @Column(name = "user_id")
    private int user_id;
}