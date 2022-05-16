package com.sandcoder.springbootunittesting.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sandcoder.springbootunittesting.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Optional<Employee> findByEmpNum(String empNum);
	void deleteByEmpNum(String empNum);
}
