/**
 * 
 */
package com.example.postgresdemo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 * @author Cybertech1
 *
 */
@Entity
public class Semester {

	/**
	 * semesterId
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer semesterId;
	/**
	 * Semester to Student
	 */
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "Student_semester", joinColumns = @JoinColumn(name = "semester_FK", referencedColumnName = "semesterId"), 
	inverseJoinColumns = @JoinColumn(name = "student_FK", referencedColumnName = "studentId"))
	private List<Student> studSemList = new ArrayList<>();

	/**
	 * Semester to Subject
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Subject.class)
	private List<Subject> subjectList = new ArrayList<>();

	
	
	/**
	 * Semester to Course
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Course.class)
	private List<Course> subjectCourse = new ArrayList<>();
	
	
	/**
	 * @return the semesterId
	 */
	public Integer getSemesterId() {
		return semesterId;
	}

	/**
	 * @param semesterId
	 *            the semesterId to set
	 */
	public void setSemesterId(final Integer semesterId) {
		this.semesterId = semesterId;
	}

	/**
	 * @return the studSemList
	 */
	public List<Student> getStudSemList() {
		return studSemList;
	}

	/**
	 * @param studSemList
	 *            the studSemList to set
	 */
	public void setStudSemList(final List<Student> studSemList) {
		this.studSemList = studSemList;
	}

	

	/**
	 * @return the subjectList
	 */
	public List<Subject> getSubjectList() {
		return subjectList;
	}

	/**
	 * @param subjectList
	 *            the subjectList to set
	 */
	public void setSubjectList(final List<Subject> subjectList) {
		this.subjectList = subjectList;
	}
	

	/**
	 * @return the subjectCourse
	 */
	public List<Course> getSubjectCourse() {
		return subjectCourse;
	}

	/**
	 * @param subjectCourse the subjectCourse to set
	 */
	public void setSubjectCourse(final List<Course> subjectCourse) {
		this.subjectCourse = subjectCourse;
	}

	/**
	 * @param semesterId
	 * @param studSemList
	 * @param subjectList
	 */
	public Semester(final Integer semesterId, final List<Student> studSemList, final List<Subject> subjectList) {
		super();
		this.semesterId = semesterId;
		this.studSemList = studSemList;
		this.subjectList = subjectList;
	}

	/**
	 * 
	 */
	public Semester() {
		super();
		// TODO Auto-generated constructor stub
	}

}
