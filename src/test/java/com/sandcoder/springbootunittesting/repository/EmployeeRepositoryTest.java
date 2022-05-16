package com.sandcoder.springbootunittesting.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sandcoder.springbootunittesting.model.Employee;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EmployeeRepositoryTest {

	@Autowired
	private EmployeeRepository employeeRepository;

	private Optional<Employee> employee;
	private Employee fetchedEmployee;
	private List<Employee> empList;
	private List<Employee> fetchedEmpList;
	private String empNum = "EMP01";

	@BeforeAll
	public void setUp() {

		this.employee = Optional.of(new Employee(1, "EMP01", "Sandep", "IT"));
		this.empList = Stream.of(new Employee(3, "EMP02", "Rahul", "IT"), new Employee(4, "EMP03", "Ankit", "CRES"),
				new Employee(5, "EMP04", "Milind", "Security")).collect(Collectors.toList());
	}

	@Test
	@DisplayName(value = "Testing CreateOne in Repo")
	@Order(value = 1)
	void testSave() {
		// Save employee
		employeeRepository.save(this.employee.get());
		// Fetch saved employee
		fetchedEmployee = this.employeeRepository.findById(this.employee.get().getId()).get();
		// Compare
		assertEquals(employee.get().getId(), fetchedEmployee.getId());
		assertEquals(employee.get().getEmpNum(), fetchedEmployee.getEmpNum() );
		assertEquals(employee.get().getEmpName(), fetchedEmployee.getEmpName() );
		assertEquals(employee.get().getEmpDept(), fetchedEmployee.getEmpDept());
	}

	@Test
	@Order(value = 2)
	@DisplayName(value = "Testing findByEmpNum in Repo")
	void testFindByEmpNum() {
		//Save employee
		employeeRepository.save(this.employee.get());
		//Fetch saved employee
		fetchedEmployee = this.employeeRepository.findByEmpNum(empNum).get();
		// Compare
		assertEquals(employee.get().getEmpNum(), fetchedEmployee.getEmpNum());
		assertEquals(employee.get().getEmpName(), fetchedEmployee.getEmpName() );
		assertEquals(employee.get().getEmpDept(), fetchedEmployee.getEmpDept() );
	}

	@Test
	@Order(value = 3)
	@DisplayName(value = "Testing findAll in Repo")
	void testFindAll() {
		//this.employeeRepository.saveAll(empList);
		for(Employee e: empList) 
			this.employeeRepository.save(e);
		fetchedEmpList = this.employeeRepository.findAll();
		assertEquals(empList.size(), fetchedEmpList.size());
		for (int i = 0; i < empList.size(); i++) {
			assertEquals(this.empList.get(i).getEmpNum(), this.fetchedEmpList.get(i).getEmpNum());
			assertEquals(this.empList.get(i).getEmpName(), this.fetchedEmpList.get(i).getEmpName());
			assertEquals(this.empList.get(i).getEmpDept(), this.fetchedEmpList.get(i).getEmpDept());
		}
	}

	@AfterAll
	void tearDown() {
		employeeRepository.deleteAll();
		fetchedEmpList = null;
		empList = null;
		employee = null;
	}

}
