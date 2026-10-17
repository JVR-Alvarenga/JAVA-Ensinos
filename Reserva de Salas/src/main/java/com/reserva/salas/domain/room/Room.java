package com.reserva.salas.domain.room;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rooms")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Room {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "is_busy")
    private Boolean isBusy;

    public Room(DataRegisterRoom data) {
        this.name = data.name();
        this.isBusy = false;
    }

    public void update(@Valid DataUpdateRoom data) {
        if (data.isBusy() != null) this.isBusy = data.isBusy();
        if (data.name() != null) this.name = data.name();
    }
}
