/**
 * 
 */
package com.onepagegallery.gateway.filesystem;

/**
 * @author shahnawazshahin
 *
 */
public class FolderDoesNotExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3004476960414096559L;

	/*
	 * Constructor for setting the message against the exception by
	 * using the constructor inherited from the RuntimeException class.
	 */
	public FolderDoesNotExistException(String message) {

		super(message);
	}
	
	/*
	 * Constructor for setting the throwable exception against this exception by
	 * using the constructor inherited from the RuntimeException class.
	 */
	public FolderDoesNotExistException(Throwable e) {

		super(e);
	}	
}
