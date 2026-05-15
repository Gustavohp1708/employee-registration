package io.github.gustavohp1708.employeeregistration.domain.dto.dtoEmployee;

import io.github.gustavohp1708.employeeregistration.domain.antities.Department;
import io.github.gustavohp1708.employeeregistration.domain.dto.dtoAddress.DtoCreateAddressRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DtoCreateEmployeeRequest(

        @NotBlank
        String name,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String phone,

        @NotBlank
        String role,

        @NotBlank
        Department department,

        @NotNull
        @Valid
        DtoCreateAddressRequest address

) {

}
