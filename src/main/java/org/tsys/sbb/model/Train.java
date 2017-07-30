package org.tsys.sbb.model;

import javax.persistence.*;

@Entity
@Table(name = "train")
public class Train {

    @Id
    @Column(name = "train_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int train_id;

    @Column(name = "seats")
    private int seats;

    @Column(name = "speed_percents")
    private int speed_percents;

    public Train() {
    }

    public int getTrain_id() {
        return train_id;
    }

    public void setTrain_id(int train_id) {
        this.train_id = train_id;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public int getSpeed_percents() {
        return speed_percents;
    }

    public void setSpeed_percents(int speed_percents) {
        this.speed_percents = speed_percents;
    }

    @Override
    public String toString() {
        return "Train{" +
                "train_id=" + train_id +
                ", seats=" + seats +
                ", speed_percents=" + speed_percents +
                '}';
    }
}