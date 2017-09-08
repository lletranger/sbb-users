package org.tsys.sbb.model;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "user")
public @Data class User implements Serializable {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int user_id;

    @NotEmpty(message = "Username is compulsory")
    @Size(min = 4)
    @Column(name = "username")
    private String username;

    @Email
    @Column(name = "email")
    private String email;

    @NotEmpty(message = "Password is compulsory")
    @Size(min = 4)
    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

}