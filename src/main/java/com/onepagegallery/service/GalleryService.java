/**
 * 
 */
package com.onepagegallery.service;

import com.onepagegallery.domain.Image;
import com.onepagegallery.domain.ImageLibrary;

/**
 * @author shahnawazshahin
 *
 * A service interface for accessing the gallery features of the applicaton.
 */
public interface GalleryService {

	/*
	 * Receive thumbnail information of images stored in the library.
	 */
	ImageLibrary retriveGallery();
	
	/*
	 * Save the image into the library.
	 */
	void saveImageToGallery(Image image);
}
