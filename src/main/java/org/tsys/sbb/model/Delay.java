package org.tsys.sbb.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "delay")
public class Delay {
    @Id
    @Column(name = "delay_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int delay_id;

    @Column(name = "board_id")
    private int board_id;

    @Column(name = "delay_time")
    @Temporal(TemporalType.TIME)
    private Date delay_time;

    public Delay() {
    }

    public int getDelay_id() {
        return delay_id;
    }

    public void setDelay_id(int delay_id) {
        this.delay_id = delay_id;
    }

    public int getBoard_id() {
        return board_id;
    }

    public void setBoard_id(int board_id) {
        this.board_id = board_id;
    }

    public Date getDelay_time() {
        return delay_time;
    }

    public void setDelay_time(Date delay_time) {
        this.delay_time = delay_time;
    }

    @Override
    public String toString() {
        return "Delay{" +
                "delay_id=" + delay_id +
                ", board_id=" + board_id +
                ", delay_time=" + delay_time +
                '}';
    }
}
