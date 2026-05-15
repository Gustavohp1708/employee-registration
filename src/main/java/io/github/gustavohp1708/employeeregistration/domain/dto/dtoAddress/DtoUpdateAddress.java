package io.github.gustavohp1708.employeeregistration.domain.dto.dtoAddress;

public record DtoUpdateAddress(

        String street,
        String neighborhood,
        String postalCode,
        String number,
        String complement,
        String city,
        String state
) {
}
