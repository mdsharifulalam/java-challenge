package jp.co.axa.apidemo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.repositories.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	/**
	 * 
	 * @param employeeRepository
	 */
	public void setEmployeeRepository(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	/**
	 * 
	 */
	public List<Employee> retrieveEmployees() {
		List<Employee> employees = employeeRepository.findAll();
		return employees;
	}

	/**
	 * 
	 * @param employeeId
	 */
	public Employee getEmployee(Long employeeId) {
		Optional<Employee> optEmp = employeeRepository.findById(employeeId);
		return optEmp.get();
	}

	/**
	 * 
	 * @param employee
	 */
	public void saveEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

	/**
	 * 
	 * @param employeeId
	 */
	public void deleteEmployee(Long employeeId) {
		employeeRepository.deleteById(employeeId);
	}

	/**
	 * 
	 * @param employee
	 */
	public void updateEmployee(Employee employee) {
		employeeRepository.save(employee);
	}
}