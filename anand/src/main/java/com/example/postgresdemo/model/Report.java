package com.example.postgresdemo.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Report {

	/**
	 * reportId
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long reportId;
	/**
	 * ReportName
	 */
	private String reportName;
	/**
	 * createdDat
	 */
	private Date createdDat;

	/**
	 * @author Cybertech1
	 *
	 */
	public enum Userrole {
		SERVICE, COUPON, NOTE, LOG
	}

	/**
	 * Report to Customer
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customerId_fk", referencedColumnName = "customerId")
	private Customer customer;
	
	 private String type;

	/**
	 * @param reportName
	 * @param createdDat
	 * @param customer
	 */
	public Report(final String reportName, final Date createdDat, final Customer customer,final String type) {
		super();
		this.reportName = reportName;
		this.createdDat = createdDat;
		this.customer = customer;
		this.type = type;
	}

	/**
	 * @return the reportId
	 */
	public long getReportId() {
		return reportId;
	}

	/**
	 * @param reportId
	 *            the reportId to set
	 */
	public void setReportId(final long reportId) {
		this.reportId = reportId;
	}

	/**
	 * @return the reportName
	 */
	public String getReportName() {
		return reportName;
	}

	/**
	 * @param reportName
	 *            the reportName to set
	 */
	public void setReportName(final String reportName) {
		this.reportName = reportName;
	}

	/**
	 * @return the createdDat
	 */
	public Date getCreatedDat() {
		return createdDat;
	}

	/**
	 * @param createdDat
	 *            the createdDat to set
	 */
	public void setCreatedDat(final Date createdDat) {
		this.createdDat = createdDat;
	}

	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer
	 *            the customer to set
	 */
	public void setCustomer(final Customer customer) {
		this.customer = customer;
	}

	/**
	 * Report
	 */
	public Report() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Report [reportId=" + reportId + ", reportName=" + reportName + ", createdDat=" + createdDat
				+ ", customer=" + customer + ", type=" + type + "]";
	}
	

}
