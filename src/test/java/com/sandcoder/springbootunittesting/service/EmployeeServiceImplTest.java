package com.sandcoder.springbootunittesting.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sandcoder.springbootunittesting.model.Employee;
import com.sandcoder.springbootunittesting.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

	@Mock
	private EmployeeRepository employeeRepository;
	
	@InjectMocks
	private EmployeeServiceImpl employeeServiceImpl;
	
	@Captor
	ArgumentCaptor<Employee> empCaptor;
	
	private Optional<Employee> employee;
	private List<Employee> empList;
	private String empNum = "EMP01";

	@BeforeEach
	public void setUp() {
		
		this.employee = Optional.of(new Employee(1, "EMP01", "Sandep", "IT"));
		this.empList = Stream.of(new Employee(2, "EMP02", "Rahul", "IT"),
				new Employee(3, "EMP03", "Ankit", "CRES"), new Employee(4, "EMP04", "Milind", "Security"))
				.collect(Collectors.toList());
	}

	@Test
	@DisplayName(value = "Testing findByEmpNum")
	void testFindByEmpNum() {
		when(employeeRepository.findByEmpNum(this.empNum)).thenReturn(this.employee);
		assertEquals(employeeServiceImpl.findByEmpNum(this.empNum), this.employee.get());
	}

	@Test
	@DisplayName(value = "Testing findAll")
	void testFindAll() {
		when(employeeRepository.findAll()).thenReturn(this.empList);
		assertEquals(employeeServiceImpl.findAll(), this.empList);
	}

	@Test
	@DisplayName(value = "Testing CreateOne")
	void testCreateOne() {
		employeeServiceImpl.createOne(this.employee.get());
		verify(employeeRepository,times(1)).save(empCaptor.capture());
		assertEquals(empCaptor.getAllValues().size(),1);
		assertEquals(empCaptor.getAllValues().get(0).getEmpName(),this.employee.get().getEmpName());
		assertEquals(empCaptor.getAllValues().get(0).getEmpDept(),this.employee.get().getEmpDept());
		assertEquals(empCaptor.getAllValues().get(0).getId(),this.employee.get().getId());	
	}

}
