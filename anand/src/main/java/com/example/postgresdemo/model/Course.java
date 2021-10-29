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
public class Course {
	/**
	 * courceId
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer courceId;

	/**
	 * subjectList
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "cource_fk", referencedColumnName = "courceId")
	private List<Subject> subjectList = new ArrayList<>();

	/**
	 * stuCourceList
	 */
	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	@JoinTable(name = "cource_Student", joinColumns = @JoinColumn(name = "cource_FK", referencedColumnName = "courceId"), inverseJoinColumns = @JoinColumn(name = "student_FK", referencedColumnName = "studentId"))
	private List<Student> stuCourceList = new ArrayList<>();

	/*
	 * @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	 * 
	 * @JoinColumn(name = "cource_fk", referencedColumnName = "courceId")
	 * private List<Student> studSubjectL = new ArrayList<>();
	 */

	/**
	 * @return the courceId
	 */
	public Integer getCourceId() {
		return courceId;
	}

	/**
	 * @param courceId
	 *            the courceId to set
	 */
	public void setCourceId(final Integer courceId) {
		this.courceId = courceId;
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
	 * @return the stuCourceList
	 */
	public List<Student> getStuCourceList() {
		return stuCourceList;
	}

	/**
	 * @param stuCourceList
	 *            the stuCourceList to set
	 */
	public void setStuCourceList(final List<Student> stuCourceList) {
		this.stuCourceList = stuCourceList;
	}

	/**
	 * @param courceId
	 * @param subjectList
	 * @param stuCourceList
	 */
	public Course(final Integer courceId, final List<Subject> subjectList, final List<Student> stuCourceList) {
		super();
		this.courceId = courceId;
		this.subjectList = subjectList;
		this.stuCourceList = stuCourceList;
	}

	/**
	 * Course
	 */
	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}

}
