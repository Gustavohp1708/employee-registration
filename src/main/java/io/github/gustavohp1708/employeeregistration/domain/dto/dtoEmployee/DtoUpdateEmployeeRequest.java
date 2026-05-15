package io.github.gustavohp1708.employeeregistration.domain.dto.dtoEmployee;

import io.github.gustavohp1708.employeeregistration.domain.antities.Address;
import io.github.gustavohp1708.employeeregistration.domain.antities.Department;
import io.github.gustavohp1708.employeeregistration.domain.dto.dtoAddress.DtoUpdateAddress;

public record DtoUpdateEmployeeRequest(

        String name,

        String email,

        String phone,

        String role,

        Department department,

        DtoUpdateAddress address

) {
}
