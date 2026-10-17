package com.reserva.salas.domain.reservation;
import com.reserva.salas.domain.employee.Employee;
import com.reserva.salas.domain.room.Room;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    public Reservation(DataRegisterReservation data, Employee employee, Room room) {
        this.employee = employee;
        this.room = room;
        this.startTime = data.startTime();
        this.endTime = data.endTime();
        this.status = Status.confirmed;
    }

    public void update(DataUpdateReservation data, Room room) {
        if (room != null) this.room = room;
        if (data.startTime() != null) this.startTime = data.startTime();
        if (data.endTime() != null) this.endTime = data.endTime();
        if (data.status() != null) this.status = data.status();
    }
}
