package io.github.gustavohp1708.employeeregistration.domain.dto.dtoAddress;

import io.github.gustavohp1708.employeeregistration.domain.entities.Address;

public record DtoAddressDetailsResponse(

        Long id,
        String street,
        String neighborhood,
        String postalCode,
        String number,
        String complement,
        String city,
        String state
) {
    public DtoAddressDetailsResponse(Address address) {
        this(
                address.getId(),
                address.getStreet(),
                address.getNeighborhood(),
                address.getPostalCode(),
                address.getNumber(),
                address.getComplement(),
                address.getCity(),
                address.getState()
        );
    }
}
