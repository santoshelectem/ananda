/**
 * 
 */
package com.example.postgresdemo.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Cybertech1
 *
 */
@Entity
@Table(name = "employ")
public class Employee {
	    /**
	     * Employee Id
	     */
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long employeeId;

	    /**
	     * employee first name
	     */
	    @Column(name = "first_name")
	    private String firstName;

	    /**
	     * employee last name
	     */
	    @Column(name = "last_name")
	    private String lastName;

	    /**
	     * employee email id
	     */
	    @Column(name = "email_id")
	    private String emailId;
	    @ManyToOne(cascade=CascadeType.ALL)
	    private Address address;
		/**
		 * @param id
		 * @param firstName
		 * @param lastName
		 * @param emailId
		 */
		public Employee(final long employeeId, final String firstName, final String lastName,final  String emailId) {
			super();
			this.employeeId = employeeId;
			this.firstName = firstName;
			this.lastName = lastName;
			this.emailId = emailId;
		}

		/**
		 * @return the firstName
		 */
		public String getFirstName() {
			return firstName;
		}

		/**
		 * @param firstName the firstName to set
		 */
		public void setFirstName(final String firstName) {
			this.firstName = firstName;
		}

		/**
		 * @return the lastName
		 */
		public String getLastName() {
			return lastName;
		}

		/**
		 * @param lastName the lastName to set
		 */
		public void setLastName(final String lastName) {
			this.lastName = lastName;
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
		 * @return the employeeId
		 */
		public Long getEmployeeId() {
			return employeeId;
		}

		/**
		 * @param employeeId the employeeId to set
		 */
		public void setEmployeeId(Long employeeId) {
			this.employeeId = employeeId;
		}
		

		/**
		 * @return the address
		 */
		public Address getAddress() {
			return address;
		}

		/**
		 * @param address the address to set
		 */
		public void setAddress(Address address) {
			this.address = address;
		}

		/**
		 * 
		 */
		public Employee() {
			super();
			// TODO Auto-generated constructor stub
		}
	    

}
