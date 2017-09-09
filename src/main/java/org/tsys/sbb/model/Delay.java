package org.tsys.sbb.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "delay")
public @Data class Delay {

    @Id
    @Column(name = "delay_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int delay_id;

    @NotNull(message = "Delay must have a board!")
    @OneToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @NotNull(message = "Delay time is compulsory")
    @Column(name = "delay_time")
    @Temporal(TemporalType.TIME)
    private Date delay_time;
}