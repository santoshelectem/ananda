/**
 * 
 */
package com.example.postgresdemo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.postgresdemo.model.Student;

/**
 * @author Cybertech1
 *
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
	/**
	 * @param studentId
	 */
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM student_semester WHERE student_fk = :studentId", nativeQuery = true)
	void deleteStuSem(@Param("studentId") Integer studentId);

}
