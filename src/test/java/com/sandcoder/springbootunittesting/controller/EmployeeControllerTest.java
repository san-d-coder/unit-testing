package com.sandcoder.springbootunittesting.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sandcoder.springbootunittesting.model.Employee;
import com.sandcoder.springbootunittesting.service.EmployeeService;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

	@Mock
	private EmployeeService empService;
	private Employee employee;
	private List<Employee> employeeList;
	private String empNumber;

	@InjectMocks
	private EmployeeController employeeController;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		employee = new Employee(1, "EMP101", "Sandeep", "IT");
		empNumber = "EMP101";
		this.employeeList = Stream.of(new Employee(3, "EMP02", "Rahul", "IT"),
				new Employee(4, "EMP03", "Ankit", "CRES"), new Employee(5, "EMP04", "Milind", "Security"))
				.collect(Collectors.toList());
		mockMvc = standaloneSetup(employeeController).build();
	}

	@Test
	@DisplayName(value = "Testing findByNum in EmployeeController")
	void testFindByEmpNum() throws Exception {
		when(empService.findByEmpNum(this.empNumber)).thenReturn(this.employee);
		mockMvc.perform(get("/employee/find/EMP101").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.empName", is(this.employee.getEmpName())))
				.andExpect(jsonPath("$.empDept", is(this.employee.getEmpDept())));

	}

	@Test
	@DisplayName(value = "Testing findAll in EmployeeController")
	void testFindAll() throws Exception {
		when(empService.findAll()).thenReturn(employeeList);
		mockMvc.perform(get("/employee/findAll").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(3))).andExpect(jsonPath("$[0].empName", is("Rahul")))
				.andExpect(jsonPath("$[1].empName", is("Ankit")));
	}

	@Test
	@DisplayName(value = "Testing createOne in EmployeeController")
	void testCreateOne() throws Exception {
		when(empService.createOne(this.employee)).thenReturn(employee);
		mockMvc.perform(post("/employee/create").contentType(MediaType.APPLICATION_JSON).content(asJsonString(this.employee)))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.empName", is(this.employee.getEmpName())))
				.andExpect(jsonPath("$.empDept", is(this.employee.getEmpDept())));
		verify(empService, times(1)).createOne(employee);
	}

	static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
