package io.github.gustavohp1708.employeeregistration.domain.controllers;

import io.github.gustavohp1708.employeeregistration.domain.dto.dtoEmployee.DtoCreateEmployeeRequest;
import io.github.gustavohp1708.employeeregistration.domain.dto.dtoEmployee.DtoEmployeeDetailsResponse;
import io.github.gustavohp1708.employeeregistration.domain.dto.dtoEmployee.DtoUpdateEmployeeRequest;
import io.github.gustavohp1708.employeeregistration.domain.services.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService service;

    @PostMapping
    public ResponseEntity<DtoEmployeeDetailsResponse> create(@RequestBody @Valid DtoCreateEmployeeRequest request){

        var response = service.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);

    }

    @GetMapping
    public ResponseEntity<Page<DtoEmployeeDetailsResponse>> findAll (Pageable pageable){

        var response = service.findAll(pageable);

        return ResponseEntity.ok(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<DtoEmployeeDetailsResponse> findById (@PathVariable Long id){
        var response = service.findById(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping
    public ResponseEntity<DtoEmployeeDetailsResponse> update (@RequestBody @Valid DtoUpdateEmployeeRequest request){
        var response = service.updateEmployee(request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DtoEmployeeDetailsResponse> deactivate (@PathVariable Long id){
        service.deactivateEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
