package io.github.gustavohp1708.employeeregistration.domain.dto.dtoEmployee;

import io.github.gustavohp1708.employeeregistration.domain.antities.Address;
import io.github.gustavohp1708.employeeregistration.domain.antities.Department;
import io.github.gustavohp1708.employeeregistration.domain.antities.Employee;
import io.github.gustavohp1708.employeeregistration.domain.dto.dtoAddress.DtoAddressDetailsResponse;


public record DtoEmployeeDetailsResponse(

        Long id,
        String name,
        String email,
        String phone,
        String role,
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
                employee.getDepartment(),
                new DtoAddressDetailsResponse(employee.getAddress()),
                employee.getActive()

        );
    }

}
