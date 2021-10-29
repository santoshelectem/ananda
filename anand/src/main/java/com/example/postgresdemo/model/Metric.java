/**
 * 
 */
package com.example.postgresdemo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Cybertech1
 *
 */
@Entity
public class Metric {
	/**
	 * metricId
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer metricId;
	/**
	 * metricName
	 */
	private String metricName;
	/**
	 * active
	 */
	private boolean active;
	/**
	 * formula
	 */
	private String formula;

	/**
	 * @return the metricId
	 */
	public Integer getMetricId() {
		return metricId;
	}

	/**
	 * @param metricId
	 *            the metricId to set
	 */
	public void setMetricId(Integer metricId) {
		this.metricId = metricId;
	}

	/**
	 * @return the metricName
	 */
	public String getMetricName() {
		return metricName;
	}

	/**
	 * @param metricName
	 *            the metricName to set
	 */
	public void setMetricName(final String metricName) {
		this.metricName = metricName;
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
	 * @return the formula
	 */
	public String getFormula() {
		return formula;
	}

	/**
	 * @param formula
	 *            the formula to set
	 */
	public void setFormula(final String formula) {
		this.formula = formula;
	}

	/**
	 * 
	 */
	public Metric() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param metricName
	 * @param active
	 * @param formula
	 */
	public Metric(final String metricName, final boolean active, final String formula) {
		super();
		this.metricName = metricName;
		this.active = active;
		this.formula = formula;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Metric [metricId=" + metricId + ", metricName=" + metricName + ", active=" + active + ", formula="
				+ formula + "]";
	}

}
