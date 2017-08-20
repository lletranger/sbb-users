package org.tsys.sbb.model;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

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

    @NotNull(message = "Board id is compulsory")
    @Range(min = 1, message = "Board ID must be positive")
    @Column(name = "board_id")
    private int board_id;

    @NotNull(message = "Delay time is compulsory")
    @Column(name = "delay_time")
    @Temporal(TemporalType.TIME)
    private Date delay_time;
}