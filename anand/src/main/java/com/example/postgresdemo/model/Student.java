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
import javax.persistence.OneToOne;

/**
 * @author Cybertech1
 *
 */
@Entity
public class Student {

	/**
	 * studentId
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer studentId;

	/**
	 * Student to course
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "course_id", referencedColumnName = "courceId")
	private Course course;

	/**
	 * Student to Subject
	 */
	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinTable(name = "Student_subject", joinColumns = @JoinColumn(name = "student_FK", referencedColumnName = "studentId"), inverseJoinColumns = @JoinColumn(name = "subject_FK", referencedColumnName = "subjectId"))
	private List<Subject> stuSubList = new ArrayList<>();

	/**
	 * Student to Subject 
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "student_fk", referencedColumnName = "studentId")
	private List<Subject> studSubjectList = new ArrayList<>();

	/**
	 * @return the studentId
	 */
	public Integer getStudentId() {
		return studentId;
	}

	/**
	 * @param studentId
	 *            the studentId to set
	 */
	public void setStudentId(final Integer studentId) {
		this.studentId = studentId;
	}

	/**
	 * @return the course
	 */
	public Course getCourse() {
		return course;
	}

	/**
	 * @param course
	 *            the course to set
	 */
	public void setCourse(final Course course) {
		this.course = course;
	}

	/**
	 * @return the stuSubList
	 */
	public List<Subject> getStuSubList() {
		return stuSubList;
	}

	/**
	 * @param stuSubList
	 *            the stuSubList to set
	 */
	public void setStuSubList(final List<Subject> stuSubList) {
		this.stuSubList = stuSubList;
	}

	/**
	 * @return the studSubjectList
	 */
	public List<Subject> getStudSubjectList() {
		return studSubjectList;
	}

	/**
	 * @param studSubjectList
	 *            the studSubjectList to set
	 */
	public void setStudSubjectList(final List<Subject> studSubjectList) {
		this.studSubjectList = studSubjectList;
	}

	/**
	 * @param studentId
	 * @param course
	 * @param stuSubList
	 */
	public Student(final Integer studentId, final Course course, final List<Subject> stuSubList) {
		super();
		this.studentId = studentId;
		this.course = course;
		this.stuSubList = stuSubList;
	}

	/**
	 * Student
	 */
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

}
