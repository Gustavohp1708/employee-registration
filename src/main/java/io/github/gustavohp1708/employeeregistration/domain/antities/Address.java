package io.github.gustavohp1708.employeeregistration.domain.antities;

import io.github.gustavohp1708.employeeregistration.domain.dto.dtoAddress.DtoCreateAddressRequest;
import io.github.gustavohp1708.employeeregistration.domain.dto.dtoAddress.DtoUpdateAddress;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_addresses")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode()
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String neighborhood;
    private String postalCode;
    private String number;
    private String complement;
    private String city;
    private String state;

    public Address(DtoCreateAddressRequest request) {
        this.street = request.street();
        this.neighborhood = request.neighborhood();
        this.postalCode = request.postalCode();
        this.number = request.number();
        this.complement = request.complement();
        this.city = request.city();
        this.state = request.state();
    }

    public void updateAddress (DtoUpdateAddress request){

        if (request.street() != null){
            this.street = request.street();
        }

        if (request.neighborhood() != null){
            this.neighborhood = request.neighborhood();
        }

        if (request.postalCode() != null){
            this.postalCode = request.postalCode();
        }

        if (request.number() != null){
            this.number = request.number();
        }

        if (request.complement() != null){
            this.complement = request.complement();
        }

        if (request.city() != null){
            this.city = request.city();
        }

        if (request.state() != null){
            this.state = request.state();
        }
    }


}
