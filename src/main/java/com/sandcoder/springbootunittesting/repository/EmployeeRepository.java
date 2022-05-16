package com.sandcoder.springbootunittesting.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sandcoder.springbootunittesting.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Optional<Employee> findByEmpNum(long empNum);
}
