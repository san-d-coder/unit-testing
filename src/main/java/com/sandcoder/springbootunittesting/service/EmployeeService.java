package com.sandcoder.springbootunittesting.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sandcoder.springbootunittesting.model.Employee;
@Service
public interface EmployeeService {

	Employee findByEmpNum(String empNum);

	List<Employee> findAll();

	Employee createOne(Employee e);
	
//	Employee deleteOne(Employee e);
//	
//	void deleteAll();

}