/**
 * 
 */
package com.example.postgresdemo.exception;

/**
 * @author Cybertech1
 *
 */
public class FileStorageException extends Exception {
	/**
	 * @param message
	 */
	public FileStorageException(final String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public FileStorageException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
