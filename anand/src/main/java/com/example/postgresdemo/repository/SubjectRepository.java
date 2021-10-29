/**
 * 
 */
package com.example.postgresdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.postgresdemo.model.Course;
import com.example.postgresdemo.model.Subject;

/**
 * @author Cybertech1
 *
 */
@Repository
public interface SubjectRepository  extends JpaRepository<Subject, Integer>{

}
