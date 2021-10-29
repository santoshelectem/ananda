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
public class User {
	/**
	 * userId
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	/**
	 * User emailId
	 */
	private String emailId;
	/**
	 * User phoneNumber
	 */
	private Integer phoneNumber;
	
	
	/**
	 * User role
	 */
	private String role;

	/**
	 * User to Customer
	 */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "userId_fk", referencedColumnName = "userId")
	private List<Customer> customers=new ArrayList<>();

	/**
	 * @author Cybertech1
	 *
	 */
	public enum Userrole {
		MANAGER, ADMIN, FIELDUSER
	}

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(final Long userId) {
		this.userId = userId;
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
	 * @return the phoneNumber
	 */
	public Integer getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(final Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the customers
	 */
	public List<Customer> getCustomers() {
		return customers;
	}

	/**
	 * @param customers
	 *            the customers to set
	 */
	public void setCustomers(final List<Customer> customers) {
		this.customers = customers;
	}

	/**
	 * @param emailId
	 * @param phoneNumber
	 * @param customers
	 */
	public User(final String emailId, final Integer phoneNumber, final List<Customer> customers) {
		super();
		this.emailId = emailId;
		this.phoneNumber = phoneNumber;
		this.customers = customers;
	}

	/**
	 * User
	 */
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [userId=" + userId + ", emailId=" + emailId + ", phoneNumber=" + phoneNumber + ", role=" + role
				+ ", customers=" + customers + "]";
	}

}
