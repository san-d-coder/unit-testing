package com.sandcoder.springbootunittesting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.sandcoder.springbootunittesting.model.Employee;
import com.sandcoder.springbootunittesting.repository.EmployeeRepository;

@Configuration
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public Employee findByEmpNum(long empNum) {
		return this.employeeRepository.findByEmpNum(empNum).get();
	}
	
	@Override
	public List<Employee> findAll(){
		return this.employeeRepository.findAll();
	}
	
	@Override
	public Employee createOne(Employee e) {
		return this.employeeRepository.save(e);
	}

}
