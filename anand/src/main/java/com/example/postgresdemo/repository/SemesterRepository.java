/**
 * 
 */
package com.example.postgresdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.postgresdemo.model.Semester;
import com.example.postgresdemo.model.Subject;

/**
 * @author Cybertech1
 *
 */
@Repository
public interface SemesterRepository extends JpaRepository<Semester, Integer> {

}
