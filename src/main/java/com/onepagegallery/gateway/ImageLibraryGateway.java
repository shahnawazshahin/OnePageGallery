/**
 * 
 */
package com.onepagegallery.gateway;

import com.onepagegallery.domain.ImageLibrary;
import com.onepagegallery.domain.Image;

/**
 * @author shahnawazshahin
 *
 * Gateway interface for loading gallery images from repository.
 * 
 */
public interface ImageLibraryGateway {

	/*
	 * Load images into the Gallery.
	 */
	void loadImageLibrary(ImageLibrary imageLibrary);
	
	/*
	 * Save the gallery image.
	 */
	void saveImage(Image image);
}
