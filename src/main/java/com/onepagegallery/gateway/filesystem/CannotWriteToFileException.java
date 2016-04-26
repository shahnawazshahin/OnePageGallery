/**
 * 
 */
package com.onepagegallery.gateway.filesystem;

/**
 * @author shahnawazshahin
 *
 */
public class CannotWriteToFileException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6244377538099723632L;

	public CannotWriteToFileException(String message) {
		
		super(message);
	}
}
