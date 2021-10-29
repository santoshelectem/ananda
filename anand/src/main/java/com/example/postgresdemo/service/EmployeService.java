/**
 * 
 */
package com.example.postgresdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.postgresdemo.exception.ResourceNotFoundException;
import com.example.postgresdemo.model.Employee;
import com.example.postgresdemo.repository.EmployeeRepository;

/**
 * @author Cybertech1
 *
 */
@Service
public class EmployeService {

	/**
	 * employeeRepository
	 */
	@Autowired
	private EmployeeRepository employeeRepository;

	/**
	 * @return
	 */
	public List<Employee> fetchAllEmployees() {
		return employeeRepository.findAll();
	}
	/**
	 * @param employeeId
	 * @return
	 */
	public Employee fetchEmployeesById(final Long employeeId) {
		Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:" + employeeId));
		 return employee;
	}

}
