package com.sandcoder.springbootunittesting.service;

import java.util.List;

import com.sandcoder.springbootunittesting.model.Employee;

public interface EmployeeService {

	Employee findByEmpNum(long empNum);

	List<Employee> findAll();

	Employee createOne(Employee e);

}