package io.github.gustavohp1708.employeeregistration.domain.antities;

import io.github.gustavohp1708.employeeregistration.domain.dto.dtoEmployee.DtoCreateEmployeeRequest;
import io.github.gustavohp1708.employeeregistration.domain.dto.dtoEmployee.DtoUpdateEmployeeRequest;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_employe")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String phone;

    private String role;

    private Department department;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    private Boolean active;

    public Employee(DtoCreateEmployeeRequest request) {
        this.name = request.name();
        this.email = request.email();
        this.phone = request.phone();
        this.role = request.role();
        this.department = request.department();
        this.address = new Address(request.address());
        this.active = true;

    }


    public void update (DtoUpdateEmployeeRequest request) {
        if (request.name() != null){
            this.name = request.name();
        }

        if (request.email() != null){
            this.email = request.email();
        }

        if (request.phone() != null){
            this.phone = request.phone();
        }

        if (request.role() != null){
            this.role = request.role();
        }

        if (request.department() != null){
            this.department = request.department();
        }

        if (request.address() != null){
            this.address.updateAddress(request.address());
        }
    }
}
