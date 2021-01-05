package com.dollar.api.models;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Accessors(chain = true)
public class Employee {
    @Id
    @GeneratedValue
    Long id;

    @NotNull
    private String name;

    @NotNull
    private String firstName;

    @NotNull
    private String phoneNumber;

    @NotNull
    @Email
    @Column(name = "email", length = 30)
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String profile = "user" ;
}
