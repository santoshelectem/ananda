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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.postgresdemo.exception.ResourceNotFoundException;
import com.example.postgresdemo.model.Course;
import com.example.postgresdemo.model.Student;
import com.example.postgresdemo.model.Subject;
import com.example.postgresdemo.repository.SubjectRepository;

/**
 * @author Cybertech1
 *
 */
@RestController
@RequestMapping("student/subject")
public class SubjectController {
	private Logger log = LoggerFactory.getLogger(SubjectController.class);

	/**
	 * subjectRepository
	 */
	@Autowired
	private SubjectRepository subjectRepository;

	/**
	 * @param subject
	 * @return
	 */
	@PostMapping("/Save")
	public Subject saveSubject(final @Valid @RequestBody Subject subject) {
		log.info("Start of SubjectController : saveSubject ");
		try {
			if (subject != null) {
				return subjectRepository.save(subject);
			}
		} catch (Exception e) {
			log.error("SubjectController: saveSubject" + e.getMessage());
		}
		log.info("end of SubjectController : saveSubject ");
		return subject;
	}

	/**
	 * @param subjectId
	 * @return
	 */
	@DeleteMapping("/subject/{subjectId}")
	public ResponseEntity<HttpStatus> deleteSubject(final @PathVariable Integer subjectId) {
		log.info("Start of SubjectController : deleteSubject ");
		final Subject subject = subjectRepository.findById(subjectId)
				.orElseThrow(() -> new ResourceNotFoundException("subject not exist with id: " + subjectId));

		subjectRepository.delete(subject);
		log.info("end of SubjectController : deleteSubject ");
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

	/**
	 * @return
	 */
	@GetMapping("/get/all")
	public List<Subject> fetch() {
		log.info("Start of SubjectController : fetch ");
		List<Subject> subject;
		try {
			subject = subjectRepository.findAll();
		} catch (ResourceNotFoundException e) {
			log.error("SubjectController: saveSubject" + e.getMessage());
			throw new ResourceNotFoundException("error");
		}
		log.info("End SubjectController : fetch ");
		return subject;
	}

	/**
	 * @param subjectId
	 * @return
	 */
	@GetMapping("/student/by/{subjectId}")
	public Optional<Subject> fetchSubjectId(final @PathVariable Integer subjectId) {
		log.info("Start of SubjectController : fetchSubjectId ");
		Optional<Subject> subject;
		try {
			subject = subjectRepository.findById(subjectId);

		} catch (ResourceNotFoundException e) {
			log.error("SubjectController: fetchSubjectId" + e.getMessage());
			throw new ResourceNotFoundException("se");
		}
		log.info("end of SubjectController : fetchSubjectId ");
		return subject;
	}
}
