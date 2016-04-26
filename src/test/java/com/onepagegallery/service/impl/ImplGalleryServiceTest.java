/**
 * 
 */
package com.onepagegallery.service.impl;

import java.awt.image.BufferedImage;

import org.junit.Before;
import org.junit.Test;

import com.onepagegallery.domain.Image;
import com.onepagegallery.gateway.ImageLibraryGateway;
import com.onepagegallery.gateway.filesystem.FilesystemImageLibraryGateway;
import com.onepagegallery.service.GalleryService;

/**
 * @author shahnawazshahin
 *
 */
public class ImplGalleryServiceTest {

	private ImageLibraryGateway imageLibraryGateway;
	private GalleryService galleryService;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		this.imageLibraryGateway = new FilesystemImageLibraryGateway("uploads-test", "uploads-test/thumbnails");
		this.galleryService = new ImplGalleryService(this.imageLibraryGateway);
	}

	@Test
	public void retriveGallerySuccess() {

		this.galleryService.retriveGallery();
	}
	
	@Test
	public void saveImageToGallerySuccess() {

		BufferedImage bufferedImage = new BufferedImage(1, 1, BufferedImage.TYPE_BYTE_GRAY);
		Image image = new Image();
		image.setOriginalFileName("onepixel.bmp");
		image.setOriginalImage(bufferedImage);
		
		this.galleryService.saveImageToGallery(image);
	}
}
