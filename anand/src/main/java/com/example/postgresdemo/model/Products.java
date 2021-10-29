/**
 * 
 */
package com.example.postgresdemo.model;

import java.sql.Date;
import java.time.LocalDateTime;
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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * @author Cybertech1
 *
 */
@Entity
public class Products {
	/**
	 * productId
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pId;
	/**
	 * name
	 */
	private String name;
	/**
	 * createDate
	 */
	@CreationTimestamp
	private LocalDateTime createDate;
	 
	/**
	 * updateDate
	 */
	@UpdateTimestamp
	private LocalDateTime updateDate;
	/**
	 * resources
	 */
	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH ,CascadeType.REMOVE}, fetch = FetchType.LAZY)
	@JoinTable(name = "product_resources", joinColumns = @JoinColumn(name = "product_FK", referencedColumnName = "pId"), inverseJoinColumns = @JoinColumn(name = "resource_FK", referencedColumnName = "rId"))
	private List<Resources> resources = new ArrayList<Resources>();
	
	/**
	 * @return the productId
	 */
	public Integer getProductId() {
		return pId;
	}
	/**
	 * @param productId the productId to set
	 */
	public void setProductId(final Integer productId) {
		this.pId = productId;
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
	 * @return the createDate
	 */
	public LocalDateTime getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(final LocalDateTime createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return the updateDate
	 */
	public LocalDateTime getUpdateDate() {
		return updateDate;
	}
	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(final LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * @return the projects
	 */
	public List<Resources> getResources() {
		return resources;
	}
	/**
	 * @param projects the projects to set
	 */
	public void setResources(final List<Resources> resources) {
		this.resources = resources;
	}
	

}
