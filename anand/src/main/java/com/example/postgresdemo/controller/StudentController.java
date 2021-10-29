/**
 * 
 */
package com.example.postgresdemo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.postgresdemo.exception.ResourceNotFoundException;
import com.example.postgresdemo.model.Student;
import com.example.postgresdemo.repository.StudentRepository;

/**
 * @author Cybertech1
 *
 */
@RestController
@RequestMapping("/student")
public class StudentController {
	
	/**
	 * StudentController Logger
	 */
	private Logger log = LoggerFactory.getLogger(StudentController.class);
	/**
	 * studentRepository
	 */
	@Autowired
	private StudentRepository studentRepository;


	/**
	 * @param student
	 * @return
	 */
	@PostMapping("/save")
	public Student saveStudent(final @Valid @RequestBody Student student) {
		log.info("start StudentController : saveStudent ");
		try {
			if (student != null) {
				return studentRepository.save(student);
			}
		} catch (Exception e) {
			log.error("Error n StudentController :: saveStudent" + e.getMessage());
		}
		log.info("end StudentController : saveStudent ");
		return student;
	}

	/**
	 * @return
	 */
	@GetMapping("/student/all")
	public List<Student> fetchStudent() {
		log.info("Start of StudentController : fetchStudent ");
		List<Student> student = null;
		try {

			student = studentRepository.findAll();

		} catch (ResourceNotFoundException e) {
			log.error("StudentController:fetchStudent" + e.getMessage());
			//throw new ResourceNotFoundException("error");
		}
		log.info("End StudentController:fetchStudent ");
		return student;
	}

	/**
	 * @param studentId
	 * @return
	 */
	@GetMapping("/student/by/{studentId}")
	public Optional<Student> fetchStudentId(final @PathVariable Integer studentId) {
		log.info("Start of StudentController : fetchStudentId : studentId:");
		Optional<Student> student = null;
		try {

			student = studentRepository.findById(studentId);

		} catch (ResourceNotFoundException e) {
			log.error("StudentController: fetchStudentId" + e.getMessage());
			//throw new ResourceNotFoundException("se");
		}
		return student;
	}

	/**
	 * @param StudentId
	 * @param studentUpdate
	 * @return
	 */
	@PutMapping("/student/{studentId}")
	public ResponseEntity<Student> updateStudent(final @PathVariable Integer studentId,
			final @RequestBody Student studentUpdate) {
		log.info("Start of StudentController : updateStudent : studentId:");
		final Student studentUpdat = studentRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("Student not exist with id: " + studentId));

		studentUpdat.setCourse(studentUpdate.getCourse());
		studentUpdat.setStuSubList(studentUpdate.getStuSubList());
		studentRepository.save(studentUpdat);
		log.info("end of StudentController : updateStudent : studentId:");
		return ResponseEntity.ok(studentUpdat);
	}

	/**
	 * @param StudentId
	 * @return
	 */
	// deleting the student
	@DeleteMapping("/student/{studentId}")
	public ResponseEntity<HttpStatus> deleteStudent(final @PathVariable Integer studentId) {
		log.info("Start of StudentController : deleteStudent : studentId:");
		final Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("student not exist with id: " + studentId));

		studentRepository.deleteStuSem(studentId);

		studentRepository.delete(student);
		log.info("end of StudentController : deleteStudent : studentId:");
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

}
