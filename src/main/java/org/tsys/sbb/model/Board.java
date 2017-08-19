package org.tsys.sbb.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "board")
public @Data class Board {

    @Id
    @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int board_id;

    @NotEmpty(message = "Board name is compulsory")
    @Column(name = "name")
    private String name;

    @NotNull(message = "Train id is compulsory")
    @Column(name = "train_id")
    private int train_id;

    @NotNull(message = "Board must have a station of origin")
    @Column(name = "from_id")
    private int from_id;

    @NotNull(message = "Board must have a destination")
    @Column(name = "to_id")
    private int to_id;

    @NotNull(message = "Board must have a departing time")
    @Column(name = "departure")
    @Temporal(TemporalType.TIME)
    private Date departure;
}