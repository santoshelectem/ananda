/**
 * 
 */
package com.example.postgresdemo.model;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Cybertech1
 *
 */
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {

	/**
	 * upload dirctory
	 */
	private String uploadDir;

	/**
	 * @return the uploadDir
	 */
	public String getUploadDir() {
		return uploadDir;
	}

	/**
	 * @param uploadDir
	 *            the uploadDir to set
	 */
	public void setUploadDir(final String uploadDir) {
		this.uploadDir = uploadDir;
	}

	/**
	 * @param uploadDir
	 */
	public FileStorageProperties(final String uploadDir) {
		super();
		this.uploadDir = uploadDir;
	}

	/**
	 * 
	 */
	public FileStorageProperties() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FileStorageProperties [uploadDir=" + uploadDir + "]";
	}

}
