package com.reserva.salas.domain.employee;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employees")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Employee {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "position")
    private String position;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;

    public Employee(DataRegisterEmployee data) {
        this.name = data.name();
        this.email = data.email();
        this.position = data.position();
        this.password = data.password();
    }


    public void update(@Valid DataUpdateEmployee data) {
        if(data.name() != null) this.name = data.name();
        if(data.email() != null) this.email = data.email();
        if(data.password() != null) this.password = data.password();
        if(data.position() != null) this.position = data.position();
    }
}
