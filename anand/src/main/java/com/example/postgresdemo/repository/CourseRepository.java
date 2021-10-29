/**
 * 
 */
package com.example.postgresdemo.repository;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.postgresdemo.model.Course;
import com.example.postgresdemo.model.Products;

/**
 * @author Cybertech1
 *
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {


}
