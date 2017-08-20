package org.tsys.sbb.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "station")
public @Data class Station {

    @Id
    @Column(name = "station_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int station_id;

    @NotEmpty(message = "Station name is compulsory")
    @Column(name = "name")
    private String name;

    @NotNull(message = "X coordinate is compulsory")
    @Range(min = -999, max = 999, message = "Station must have X coordinate")
    @Column(name = "x")
    private Integer x;

    @NotNull(message = "Y coordinate is compulsory")
    @Range(min = -999, max = 999, message = "Station must have Y coordinate")
    @Column(name = "y")
    private Integer y;
}