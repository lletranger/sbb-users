package org.tsys.sbb.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "passenger")
public class Passenger {
    @Id
    @Column(name = "pass_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int pass_id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birth_date;

    public Passenger() {
    }

    public int getPass_id() {
        return pass_id;
    }

    public void setPass_id(int pass_id) {
        this.pass_id = pass_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "pass_id=" + pass_id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birth_date=" + birth_date +
                '}';
    }
}