package jp.co.axa.apidemo;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.repositories.EmployeeRepository;

/**
 * @author md.shariful
 * 
 * Test depends on test case design
 * here i write, just some sample Test
 * 
 */
public class ApiTest extends ApiDemoApplicationTests {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void createEmployeeTest_MethodPOST() throws Exception {
		Employee emp = Employee.builder().id((long) 1).name("test").salary(2000).department("test-dept").build();
		String empJsonStr = new ObjectMapper().writeValueAsString(emp);

		mockMvc.perform(post("/api/v1/employees").contentType(MediaType.APPLICATION_JSON).content(empJsonStr))
				.andExpect(status().isOk());
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void employeeCountTest() throws Exception {
		Employee emp1 = Employee.builder().id((long) 2).name("test1").salary(2000).department("test-dept1").build();
		employeeRepository.saveAndFlush(emp1);

		Employee emp2 = Employee.builder().id((long) 3).name("test2").salary(2000).department("test-dept2").build();
		employeeRepository.saveAndFlush(emp2);

		List<Employee> empList = employeeRepository.findAll();
		assertThat(empList.size()).isEqualTo(2);
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void requestNotFoundTest_MethodGET() throws Exception {
		mockMvc.perform(get("/api/v1/employees2/{employeeId}", (long) 1).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void getEmployeeTest_MethodGET() throws Exception {
		Employee emp = Employee.builder().id((long) 4).name("test1").salary(2000).department("test-dept1").build();
		employeeRepository.saveAndFlush(emp);

		mockMvc.perform(get("/api/v1/employees/{employeeId}", (long) 1).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.msg")
						.value("{\"id\":1,\"name\":\"test1\",\"salary\":2000,\"department\":\"test-dept1\"}"));
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void deleteEmployeeTest_MethodDELETE() throws Exception {
		Employee emp = Employee.builder().id((long) 5).name("test1").salary(2000).department("test-dept1").build();
		employeeRepository.saveAndFlush(emp);

		mockMvc.perform(delete("/api/v1/employees/{employeeId}", (long) 1).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

		List<Employee> empList = employeeRepository.findAll();
		assertThat(empList.size()).isEqualTo(1);
	}

	/**
	 * 
	 * @throws Exception
	 */
	@Test
	public void updateEmployeeTest_MethodPUT() throws Exception {
		Employee emp = Employee.builder().id((long) 1).name("test1").salary(2000).department("test-dept1").build();
		String empJsonStr = new ObjectMapper().writeValueAsString(emp);

		mockMvc.perform(put("/api/v1/employees/{employeeId}", (long) 1).contentType(MediaType.APPLICATION_JSON)
				.content(empJsonStr)).andExpect(status().isOk());
	}

}
