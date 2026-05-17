package io.github.gustavohp1708.employeeregistration.domain.dto.dtoEmployee;

import io.github.gustavohp1708.employeeregistration.domain.entities.Department;
import io.github.gustavohp1708.employeeregistration.domain.entities.Employee;
import io.github.gustavohp1708.employeeregistration.domain.dto.dtoAddress.DtoAddressDetailsResponse;

import java.math.BigDecimal;
import java.time.LocalDate;


public record DtoEmployeeDetailsResponse(

        Long id,
        String name,
        String email,
        String phone,
        String role,
        BigDecimal salary,
        LocalDate admissionDate,
        LocalDate deactivationDate,
        Department department,
        DtoAddressDetailsResponse address,
        Boolean active
) {

    public DtoEmployeeDetailsResponse(Employee employee){
        this(
                employee.getId(),
                employee.getName(),
                employee.getEmail(),
                employee.getPhone(),
                employee.getRole(),
                employee.getSalary(),
                employee.getAdmissionDate(),
                employee.getDeactivationDate(),
                employee.getDepartment(),
                new DtoAddressDetailsResponse(employee.getAddress()),
                employee.getActive()

        );
    }

}
