/**
 * 
 */
package com.onepagegallery.controller;

/**
 * @author shahnawazshahin
 *
 */
public class FileNotImageException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -724522804375516682L;

	public FileNotImageException(String message) {
		
		super(message);
	}
}
