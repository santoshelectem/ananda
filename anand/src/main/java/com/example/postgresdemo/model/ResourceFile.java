/**
 * 
 */
package com.example.postgresdemo.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * @author Cybertech1
 *
 */
@Entity
public class ResourceFile {
	/**
	 * ResourceFile fileId
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer fileId;
	
	/**
	 * ResourceFile name
	 */
	private String name;
	
	/**
	 * ResourceFile
	 */
	@CreationTimestamp
	private LocalDateTime createDate;

	/**
	 * ResourceFile updateDate
	 */
	@UpdateTimestamp
	private LocalDateTime updateDate;

	/**
	 * @return the fileId
	 */
	public Integer getFileId() {
		return fileId;
	}

	/**
	 * @param fileId the fileId to set
	 */
	public void setFileId(final Integer fileId) {
		this.fileId = fileId;
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
}
