package io.github.gustavohp1708.employeeregistration.domain.repositories;

import io.github.gustavohp1708.employeeregistration.domain.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
