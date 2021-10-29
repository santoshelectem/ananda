/**
 * 
 */
package com.example.postgresdemo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
public class MeasurandPanel {
	/**
	 * panelId
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer panelId;
	/**
	 * panelName
	 */
	private String panelName;
	/**
	 * panelDescription
	 */
	private String panelDescription;
	/**
	 * 
	 */
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "panelId")
	private List<Metric> metric = new ArrayList<>();

	/**
	 * @return the panelId
	 */
	public Integer getPanelId() {
		return panelId;
	}

	/**
	 * @param panelId
	 *            the panelId to set
	 */
	public void setPanelId(final Integer panelId) {
		this.panelId = panelId;
	}

	/**
	 * @return the panelName
	 */
	public String getPanelName() {
		return panelName;
	}

	/**
	 * @param panelName
	 *            the panelName to set
	 */
	public void setPanelName(final String panelName) {
		this.panelName = panelName;
	}

	/**
	 * @return the panelDescription
	 */
	public String getPanelDescription() {
		return panelDescription;
	}

	/**
	 * @param panelDescription
	 *            the panelDescription to set
	 */
	public void setPanelDescription(final String panelDescription) {
		this.panelDescription = panelDescription;
	}

	/**
	 * @return the metric
	 */
	public List<Metric> getMetric() {
		return metric;
	}

	/**
	 * @param metric
	 *            the metric to set
	 */
	public void setMetric(final List<Metric> metric) {
		this.metric = metric;
	}

	/**
	 * 
	 */
	public MeasurandPanel() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param panelName
	 * @param panelDescription
	 * @param metric
	 */
	public MeasurandPanel(String panelName, String panelDescription, List<Metric> metric) {
		super();
		this.panelName = panelName;
		this.panelDescription = panelDescription;
		this.metric = metric;
	}

}
