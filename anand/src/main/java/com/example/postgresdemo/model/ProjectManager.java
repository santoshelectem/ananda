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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * @author Cybertech1
 *
 */
/**
 * @author Cybertech1
 *
 */
/**
 * @author Cybertech1
 *
 */
/**
 * @author Cybertech1
 *
 */
/**
 * @author Cybertech1
 *
 */
@Entity
public class ProjectManager {
	/**
	 * ProjectManager id
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pid;
	/**
	 * ProjectManager name
	 */
	private String name;
	/**
	 * ProjectManager enailId
	 */
	private String enailId;
	/**
	 * ProjectManager country
	 */
	private String country;
	
	/**
	 * ProjectManager to projects
	 */
	@ManyToMany(cascade={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch=FetchType.LAZY)
	@JoinTable(name="project_Manager_project",
		joinColumns = @JoinColumn(name="project_manager_FK", referencedColumnName="pid"),
		inverseJoinColumns = @JoinColumn(name="project_FK", referencedColumnName="projectId"))
	private List<Project> projects=new ArrayList<>(); 
	/**
	 * @param pid
	 * @param name
	 * @param enailId
	 * @param country
	 */
	public ProjectManager(final  String name, final String enailId,final String country) {
		super();
		this.pid = pid;
		this.name = name;
		this.enailId = enailId;
		this.country = country;
	}

	/**
	 * @return the pid
	 */
	public Integer getPid() {
		return pid;
	}

	/**
	 * @param pid
	 *            the pid to set
	 */
	public void setPid(final Integer pid) {
		this.pid = pid;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @return the enailId
	 */
	public String getEnailId() {
		return enailId;
	}

	/**
	 * @param enailId
	 *            the enailId to set
	 */
	public void setEnailId(final String enailId) {
		this.enailId = enailId;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * 
	 */
	public ProjectManager() {
		super();
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(final String country) {
		this.country = country;
	}

	/**
	 * @return the projects
	 */
	public List<Project> getProjects() {
		return projects;
	}

	/**
	 * @param projects the projects to set
	 */
	public void setProjects(final List<Project> projects) {
		this.projects = projects;
	}

}
