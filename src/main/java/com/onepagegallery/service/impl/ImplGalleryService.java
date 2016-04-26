/**
 * 
 */
package com.onepagegallery.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.onepagegallery.domain.Image;
import com.onepagegallery.domain.ImageLibrary;
import com.onepagegallery.gateway.ImageLibraryGateway;
import com.onepagegallery.service.GalleryService;

/**
 * @author shahnawazshahin
 *
 * An implementation of the GalleryService.
 */
public class ImplGalleryService implements GalleryService {

	private ImageLibraryGateway imageLibraryGateway;
	
	@Autowired
	public ImplGalleryService(ImageLibraryGateway imageLibraryGateway) {
		
		this.imageLibraryGateway = imageLibraryGateway;
	}

	/* (non-Javadoc)
	 * @see com.onepagegallery.service.GalleryService#retriveGallery()
	 */
	@Override
	public ImageLibrary retriveGallery() {

		ImageLibrary imageLibrary = new ImageLibrary(this.imageLibraryGateway);
		imageLibrary.loadImages();
		
		return imageLibrary;
	}

	/* (non-Javadoc)
	 * @see com.onepagegallery.service.GalleryService#saveImageToGallery(Image)
	 */	
	@Override
	public void saveImageToGallery(Image image) {

		ImageLibrary imageLibrary = new ImageLibrary(this.imageLibraryGateway);
		imageLibrary.saveImageToLibrary(image);
	}
}
