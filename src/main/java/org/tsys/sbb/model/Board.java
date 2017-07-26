package org.tsys.sbb.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "board")
public class Board {

    @Id
    @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int board_id;

    @Column(name = "name")
    private String name;

    @Column(name = "train_id")
    private int train_id;

    @Column(name = "from_id")
    private int from_id;

    @Column(name = "to_id")
    private int to_id;

    @Column(name = "role")
    private String role;

    @Column(name = "departure")
    @Temporal(TemporalType.TIME)
    private Date departure;

    public Board() {
    }

    public int getBoard_id() {
        return board_id;
    }

    public void setBoard_id(int board_id) {
        this.board_id = board_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTrain_id() {
        return train_id;
    }

    public void setTrain_id(int train_id) {
        this.train_id = train_id;
    }

    public int getFrom_id() {
        return from_id;
    }

    public void setFrom_id(int from_id) {
        this.from_id = from_id;
    }

    public int getTo_id() {
        return to_id;
    }

    public void setTo_id(int to_id) {
        this.to_id = to_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    @Override
    public String toString() {
        return "Board{" +
                "board_id=" + board_id +
                ", name='" + name + '\'' +
                ", train_id=" + train_id +
                ", from_id=" + from_id +
                ", to_id=" + to_id +
                ", role='" + role + '\'' +
                ", departure=" + departure +
                '}';
    }
}
