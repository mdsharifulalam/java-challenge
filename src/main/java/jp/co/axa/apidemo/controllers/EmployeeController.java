package jp.co.axa.apidemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.model.Response;
import jp.co.axa.apidemo.services.EmployeeService;

/**
 * @author md.shariful
 *
 */
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	/**
	 * 
	 * @param headers
	 * @return
	 */
	@GetMapping("/employees")
	public ResponseEntity<Response> getEmployees(@RequestHeader HttpHeaders headers) {
		
		/**
		 * 
		 * TODO(basic) 
		 * API Authentication(Basic, Bearer, X-API-KEY) check using HttpHeaders
		 * Sample authentication writen in Common.java
		 * HttpHeadersを使用し、API認証(Basic, Bearer, X-API-KEY)を確認する
		 * API認証用のサンプルの関数を「Common.java」に書かれています。
		 * 
		 */
		
		
		String respose = "";
		try {
			respose = new ObjectMapper().writeValueAsString(employeeService.retrieveEmployees());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			new ResponseEntity<>(new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), respose),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(new Response(HttpStatus.OK.value(), respose), HttpStatus.OK);
	}

	/**
	 * 
	 * @param employeeId
	 * @param headers
	 * @return
	 */
	@GetMapping("/employees/{employeeId}")
	public ResponseEntity<Response> getEmployee(@PathVariable(name = "employeeId") Long employeeId,
			@RequestHeader HttpHeaders headers) {
		
		/**
		 * 
		 * TODO(basic) 
		 * API Authentication(Basic, Bearer, X-API-KEY) check using HttpHeaders
		 * Sample authentication writen in Common.java
		 * HttpHeadersを使用し、API認証(Basic, Bearer, X-API-KEY)を確認する
		 * API認証用のサンプルの関数を「Common.java」に書かれています。
		 * 
		 */
		
		
		String respose = "";
		try {
			respose = new ObjectMapper().writeValueAsString(employeeService.getEmployee(employeeId));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			new ResponseEntity<>(new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), respose),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(new Response(HttpStatus.OK.value(), respose), HttpStatus.OK);
	}

	/**
	 * 
	 * @param employee
	 * @return
	 */
	@PostMapping("/employees")
	public ResponseEntity<Response> saveEmployee(@RequestBody Employee employee) {
		
		/**
		 * 
		 * TODO(basic) 
		 * API Authentication(Basic, Bearer, X-API-KEY) check using HttpHeaders
		 * Sample authentication writen in Common.java
		 * HttpHeadersを使用し、API認証(Basic, Bearer, X-API-KEY)を確認する
		 * API認証用のサンプルの関数を「Common.java」に書かれています。
		 * 
		 */
		
		
		String respose = "";

		try {
			employeeService.saveEmployee(employee);
		} catch (Exception e) {
			e.printStackTrace();
			new ResponseEntity<>(new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), respose),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			respose = "saved";
		}

		System.out.println("Employee Saved Successfully");
		return new ResponseEntity<>(new Response(HttpStatus.OK.value(), respose), HttpStatus.OK);
	}

	/**
	 * 
	 * @param employeeId
	 * @return
	 */
	@DeleteMapping("/employees/{employeeId}")
	public ResponseEntity<Response> deleteEmployee(@PathVariable(name = "employeeId") Long employeeId) {
		
		/**
		 * 
		 * TODO(basic) 
		 * API Authentication(Basic, Bearer, X-API-KEY) check using HttpHeaders
		 * Sample authentication writen in Common.java
		 * HttpHeadersを使用し、API認証(Basic, Bearer, X-API-KEY)を確認する
		 * API認証用のサンプルの関数を「Common.java」に書かれています。
		 * 
		 */
		
		
		String respose = "";
		try {
			employeeService.deleteEmployee(employeeId);
		} catch (Exception e) {
			e.printStackTrace();
			new ResponseEntity<>(new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), respose),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			respose = "deleted";
		}

		System.out.println("Employee Deleted Successfully");
		return new ResponseEntity<>(new Response(HttpStatus.OK.value(), respose), HttpStatus.OK);
	}

	/**
	 * 
	 * @param employee
	 * @param employeeId
	 * @return
	 */
	@PutMapping("/employees/{employeeId}")
	public ResponseEntity<Response> updateEmployee(@RequestBody Employee employee,
			@PathVariable(name = "employeeId") Long employeeId) {
		
		/**
		 * 
		 * TODO(basic) 
		 * API Authentication(Basic, Bearer, X-API-KEY) check using HttpHeaders
		 * Sample authentication writen in Common.java
		 * HttpHeadersを使用し、API認証(Basic, Bearer, X-API-KEY)を確認する
		 * API認証用のサンプルの関数を「Common.java」に書かれています。
		 * 
		 */
		
		
		String respose = "";

		try {
			Employee emp = employeeService.getEmployee(employeeId);
			if (emp != null) {
				employeeService.updateEmployee(employee);
			}
		} catch (Exception e) {
			e.printStackTrace();
			new ResponseEntity<>(new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), respose),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			respose = "updated";
		}

		return new ResponseEntity<>(new Response(HttpStatus.OK.value(), respose), HttpStatus.OK);

	}

}
