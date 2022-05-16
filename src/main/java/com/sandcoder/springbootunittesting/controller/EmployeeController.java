package com.sandcoder.springbootunittesting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sandcoder.springbootunittesting.model.Employee;
import com.sandcoder.springbootunittesting.service.EmployeeService;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping(value = "/find/{empNum}")
	public Employee findByEmpNum(@PathVariable(name = "empNum") long empNum) {
		return this.employeeService.findByEmpNum(empNum);
	}

	@GetMapping(value = "/findAll")
	public List<Employee> findAll() {
		return this.employeeService.findAll();
	}

	@PostMapping(value = "/create")
	public Employee createOne(@RequestBody Employee e) {

		return this.employeeService.createOne(e);
	}

}
