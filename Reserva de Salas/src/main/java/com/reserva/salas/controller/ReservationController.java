package com.reserva.salas.controller;

import com.reserva.salas.domain.employee.Employee;
import com.reserva.salas.domain.reservation.*;
import com.reserva.salas.domain.room.Room;
import com.reserva.salas.repository.EmployeeRepository;
import com.reserva.salas.repository.ReservationRepository;
import com.reserva.salas.repository.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private ReservationRepository repository;
    private EmployeeRepository employeeRepository;
    private RoomRepository roomRepository;

    public ReservationController(ReservationRepository repository, EmployeeRepository employeeRepository, RoomRepository roomRepository) {
        this.repository = repository;
        this.employeeRepository = employeeRepository;
        this.roomRepository = roomRepository;
    }

    //Routes ---------------------------------------------------------------

    @PostMapping("/register")
    @Transactional
    public ResponseEntity<DataDetailReservation> register(@RequestBody @Valid DataRegisterReservation data, UriComponentsBuilder uriBuilder) {
        Employee employee = employeeRepository.findById(data.employeeId())
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
        Room room = roomRepository.findById(data.roomId())
                .orElseThrow(() -> new EntityNotFoundException("Room not found"));

        Reservation reservation = new Reservation(data, employee, room);
        repository.save(reservation);

        var uri = uriBuilder.path("/reservation/detail/{id}").buildAndExpand(reservation.getId()).toUri();

        return ResponseEntity.created(uri).body(new DataDetailReservation(reservation));
    }

    @GetMapping("/list")
    public ResponseEntity<Page<DataListReservation>> list(Pageable pageable) {
        var page = repository.findAll(pageable).map(DataListReservation::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<DataDetailReservation> detail(@PathVariable Long id) {
        Reservation reservation = repository.getReferenceById(id);

        return ResponseEntity.ok(new DataDetailReservation(reservation));
    }

    @PutMapping("/update")
    @Transactional
    public ResponseEntity<DataDetailReservation> update(@RequestBody @Valid DataUpdateReservation data) {
        Reservation reservation = repository.getReferenceById(data.id());

        Room room = null;
        if (data.roomId() != null) {
            room = roomRepository.findById(data.roomId())
                    .orElseThrow(() -> new EntityNotFoundException("Room not found"));
        }
        reservation.update(data, room);

        return ResponseEntity.ok(new DataDetailReservation(reservation));
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
