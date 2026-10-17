package com.reserva.salas.controller;

import com.reserva.salas.domain.room.DataDetailRoom;
import com.reserva.salas.domain.room.DataRegisterRoom;
import com.reserva.salas.domain.room.DataUpdateRoom;
import com.reserva.salas.domain.room.Room;
import com.reserva.salas.repository.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/room")
public class RoomController {
    private RoomRepository repository;

    public RoomController(RoomRepository repository) {
        this.repository = repository;
    }

    // Routes ------------------------------------------------------

    @PostMapping("/add")
    public ResponseEntity<DataDetailRoom> register(@RequestBody @Valid DataRegisterRoom data) {
        Room room = new Room(data);
        repository.save(room);

        return ResponseEntity.ok(new DataDetailRoom(room));
    }

    @GetMapping("/list")
    public ResponseEntity<Page<DataDetailRoom>> list(Pageable pageable) {
        var list = repository.findAll(pageable).map(DataDetailRoom::new);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<DataDetailRoom> detail(@PathVariable Long id) {
        Room room = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Room not found"));

        return ResponseEntity.ok(new DataDetailRoom(room));
    }

    @PutMapping("/update")
    @Transactional
    public ResponseEntity<DataDetailRoom> update(@RequestBody @Valid DataUpdateRoom data) {
        Room room = repository.findById(data.id())
                .orElseThrow(() -> new EntityNotFoundException("Room not found"));
        room.update(data);

        return ResponseEntity.ok(new DataDetailRoom(room));
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        if(!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
