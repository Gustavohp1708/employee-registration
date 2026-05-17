package io.github.gustavohp1708.employeeregistration.domain.dto.dtoEmployee;

import io.github.gustavohp1708.employeeregistration.domain.entities.Department;
import io.github.gustavohp1708.employeeregistration.domain.dto.dtoAddress.DtoCreateAddressRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

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

        @NotNull
        @Positive
        BigDecimal salary,

        @NotNull
        LocalDate admissionDate,

        LocalDate deactivationDate,

        @NotNull
        Department department,

        @NotNull
        @Valid
        DtoCreateAddressRequest address

) {

}
