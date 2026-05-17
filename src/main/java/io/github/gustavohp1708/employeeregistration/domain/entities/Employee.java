package io.github.gustavohp1708.employeeregistration.domain.entities;

import io.github.gustavohp1708.employeeregistration.domain.dto.dtoEmployee.DtoCreateEmployeeRequest;
import io.github.gustavohp1708.employeeregistration.domain.dto.dtoEmployee.DtoUpdateEmployeeRequest;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "employees")
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

    private BigDecimal salary;

    @Column(name = "admission_date")
    private LocalDate admissionDate;

    @Column(name = "deactivation_date")
    private LocalDate deactivationDate;

    @Enumerated(EnumType.STRING)
    private Department department;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    private Boolean active;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Employee(DtoCreateEmployeeRequest request) {
        this.name = request.name();
        this.email = request.email();
        this.phone = request.phone();
        this.role = request.role();
        this.salary = request.salary();
        this.admissionDate = request.admissionDate();
        this.deactivationDate = request.deactivationDate();
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

        if (request.salary() != null){
            this.salary = request.salary();
        }

        if (request.admissionDate() != null){
            this.admissionDate = request.admissionDate();
        }

        if (request.deactivationDate() != null){
            this.deactivationDate = request.deactivationDate();
        }

        if (request.department() != null){
            this.department = request.department();
        }

        if (request.address() != null){
            this.address.updateAddress(request.address());
        }
    }

    public void deactivate () {
        this.active = false;
        this.deactivationDate = LocalDate.now();
    }
}
