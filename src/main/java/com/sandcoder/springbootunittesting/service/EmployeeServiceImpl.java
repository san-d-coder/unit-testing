package com.sandcoder.springbootunittesting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sandcoder.springbootunittesting.model.Employee;
import com.sandcoder.springbootunittesting.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public Employee findByEmpNum(String empNum) {
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
