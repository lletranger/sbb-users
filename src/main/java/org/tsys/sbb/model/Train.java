package org.tsys.sbb.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "train")
public @Data class Train {

    @Id
    @Column(name = "train_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int train_id;

    @Column(name = "seats")
    private int seats;

    @Column(name = "speed_percents")
    private int speed_percents;
}