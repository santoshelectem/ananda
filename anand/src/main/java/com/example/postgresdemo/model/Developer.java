/**
 * 
 */
package com.example.postgresdemo.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


/**
 * @author Cybertech1
 *
 */
@Entity
public class Developer {
	/**
	 * developerId
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer developerId;
	/**
	 * emailId
	 */
	private String emailId;
	/**
	 * name
	 */
	private String name;
	
	/**
	 * status
	 */
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="statusId")
	private Status status;
	/**
	 * @return the developerId
	 */
	public Integer getDeveloperId() {
		return developerId;
	}
	/**
	 * @param developerId the developerId to set
	 */
	public void setDeveloperId(final Integer developerId) {
		this.developerId = developerId;
	}
	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}
	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(final String emailId) {
		this.emailId = emailId;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}
	/**
	 * @return the status
	 */
	public Status getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(final Status status) {
		this.status = status;
	}
	/**
	 * 
	 */
	public Developer() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param developerId
	 * @param name
	 * @param status
	 */
	public Developer(Integer developerId, String name, Status status) {
		super();
		this.developerId = developerId;
		this.name = name;
		this.status = status;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Developer [developerId=" + developerId + ", emailId=" + emailId + ", name=" + name + ", status="
				+ status + "]";
	}
	

}
