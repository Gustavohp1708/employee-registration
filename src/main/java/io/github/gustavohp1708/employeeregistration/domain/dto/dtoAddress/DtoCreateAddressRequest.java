package io.github.gustavohp1708.employeeregistration.domain.dto.dtoAddress;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DtoCreateAddressRequest(

        @NotBlank
        String street,

        @NotBlank
        String neighborhood,

        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String postalCode,

        String number,

        String complement,

        @NotBlank
        String city,

        @NotBlank
        String state
) {
}
