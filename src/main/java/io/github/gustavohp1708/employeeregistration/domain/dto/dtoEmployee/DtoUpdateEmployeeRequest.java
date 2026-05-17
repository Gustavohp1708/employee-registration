package io.github.gustavohp1708.employeeregistration.domain.dto.dtoEmployee;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.gustavohp1708.employeeregistration.domain.entities.Department;
import io.github.gustavohp1708.employeeregistration.domain.dto.dtoAddress.DtoUpdateAddress;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DtoUpdateEmployeeRequest(

        @NotNull
        Long id,

        String name,

        String email,

        String phone,

        String role,

        @Positive
        BigDecimal salary,

        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDate admissionDate,

        @JsonFormat(pattern = "dd-MM-yyyy")
        LocalDate deactivationDate,

        Department department,

        DtoUpdateAddress address

) {

}
