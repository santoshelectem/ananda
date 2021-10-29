/**
 * 
 */
package com.example.postgresdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.postgresdemo.model.Employee;

/**
 * @author Cybertech1
 *
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
