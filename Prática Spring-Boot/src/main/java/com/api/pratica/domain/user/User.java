package com.api.pratica.domain.user;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @Table(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "telephone")
    private String telephone;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "active")
    private Boolean active;

    public User(DataRegisterUser data) {
        this.name = data.name();
        this.telephone = data.telephone();
        this.email = data.email();
        this.password = data.password();
        this.active = true;
    }

    public void updateData(DataUpdateUser data) {
        if (data.name() != null) this.name = data.name();
        if (data.telephone() != null) this.telephone = data.telephone();
        if (data.email() != null) this.email = data.email();
        if (data.password() != null) this.password = data.password();
    }

    public void delete() {
        this.active = false;
    }
}
