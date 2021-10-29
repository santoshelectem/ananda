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
import javax.persistence.OneToMany;

/**
 * @author Cybertech1
 *
 */
@Entity
public class Customer {

	/**
	 * customerId
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customerId;

	/**
	 * name
	 */
	private String name;

	/**
	 * emailId
	 */
	private String emailId;

	/**
	 * active
	 */
	private boolean active;

	/**
	 * Customer to Report reports
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "customerId_fk", referencedColumnName = "customerId")
	private List<Report> reports = new ArrayList<>();

	/**
	 * @param customerId
	 * @param name
	 * @param active
	 * @param reports
	 */
	public Customer(final String name, final boolean active, final List<Report> reports) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.active = active;
		this.reports = reports;
	}

	/**
	 * @return the customerId
	 */
	public Integer getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId
	 *            the customerId to set
	 */
	public void setCustomerId(final Integer customerId) {
		this.customerId = customerId;
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
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId
	 *            the emailId to set
	 */
	public void setEmailId(final String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active
	 *            the active to set
	 */
	public void setActive(final boolean active) {
		this.active = active;
	}

	/**
	 * @return the reports
	 */
	public List<Report> getReports() {
		return reports;
	}

	/**
	 * @param reports
	 *            the reports to set
	 */
	public void setReports(final List<Report> reports) {
		this.reports = reports;
	}

	/**
	 * Customer
	 */
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

}
