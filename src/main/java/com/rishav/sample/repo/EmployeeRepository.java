package com.rishav.sample.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rishav.sample.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Optional<Employee> getByFullName(String name);

}
