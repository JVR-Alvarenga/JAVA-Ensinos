package com.reserva.salas.controller;

import com.reserva.salas.domain.employee.*;
import com.reserva.salas.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeRepository repository;

    public EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    //Routes ---------------------------------------------------------------

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity register(@RequestBody @Valid DataRegisterEmployee data, UriComponentsBuilder uriBuilder) {
        Employee employee = new Employee(data);
        repository.save(employee);

        var uri = uriBuilder.path("/employee/detail/{id}").buildAndExpand(employee.getId()).toUri();

        return ResponseEntity.created(uri).body(new DataDetailEmployee(employee));
    }

    @GetMapping("/list")
    public ResponseEntity<Page<DataListEmployee>> list(Pageable pageable) {
        var page = repository.findAll(pageable).map(DataListEmployee::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<DataDetailEmployee> detailEmployee(@PathVariable Long id) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));

        return ResponseEntity.ok(new DataDetailEmployee(employee));
    }

    @PutMapping("/update")
    @Transactional
    public ResponseEntity<DataDetailEmployee> update(@RequestBody @Valid DataUpdateEmployee data) {
        Employee employee = repository.findById(data.id())
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
        employee.update(data);

        return ResponseEntity.ok(new DataDetailEmployee(employee));
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
