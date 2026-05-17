package io.github.gustavohp1708.employeeregistration.domain.services;

import io.github.gustavohp1708.employeeregistration.domain.dto.dtoEmployee.DtoUpdateEmployeeRequest;
import io.github.gustavohp1708.employeeregistration.domain.entities.Employee;
import io.github.gustavohp1708.employeeregistration.domain.dto.dtoEmployee.DtoCreateEmployeeRequest;
import io.github.gustavohp1708.employeeregistration.domain.dto.dtoEmployee.DtoEmployeeDetailsResponse;
import io.github.gustavohp1708.employeeregistration.domain.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repository;

    public DtoEmployeeDetailsResponse create (DtoCreateEmployeeRequest request){

        var employee = new Employee(request);

        repository.save(employee);

        return new DtoEmployeeDetailsResponse(employee);
    }

    public Page<DtoEmployeeDetailsResponse> findAll (Pageable pageable){
        return repository.findAll(pageable).map(DtoEmployeeDetailsResponse::new);
    }

    public DtoEmployeeDetailsResponse findById (@PathVariable Long id){
        var employee = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee with id " + id + " not found"));

        return new DtoEmployeeDetailsResponse(employee);
    }

    @Transactional
    public DtoEmployeeDetailsResponse updateEmployee (DtoUpdateEmployeeRequest request){

        var employee = repository.findById(request.id())
                .orElseThrow(() -> new RuntimeException("Employee with id " + request.id() + " not found"));

        employee.update(request);

        return  new DtoEmployeeDetailsResponse(employee);
    }

    @Transactional
    public void deactivateEmployee (Long id){
        var employee = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee with id " + id + " not found"));

        employee.deactivate();
    }

}
