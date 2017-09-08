package org.tsys.sbb.model;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "passenger")
public @Data class Passenger implements Serializable {

    @Id
    @Column(name = "pass_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int pass_id;

    @NotEmpty(message = "Passenger name is compulsory")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Passenger surname is compulsory")
    @Column(name = "surname")
    private String surname;

    @NotNull(message = "Passenger birth date is compulsory")
    @Past(message = "Passenger birth date must be in past!")
    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birth_date;
}